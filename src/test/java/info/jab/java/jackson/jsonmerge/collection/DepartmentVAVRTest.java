package info.jab.java.jackson.jsonmerge.collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;

class DepartmentVAVRTest {

    @Test
    public void should_MergeDepartmentEmployees_WhenUpdatingWithNewAndExistingEmployees() throws Exception {
        // Given
        DepartmentVAVR department = new DepartmentVAVR("IT");
        department.addEmployee(new Employee("1", "John")); 
        department.addEmployee(new Employee("2", "Alice"));

        // When
        String updateJson = """
            {
                "name": "IT Department", 
                "employees": [
                    {"id": "2", "name": "Alice"},
                    {"id": "3", "name": "Bob"}
                ]
            }
            """;

        ObjectMapper mapper = new ObjectMapper();
        DepartmentVAVR updatedDepartment = mapper.readerForUpdating(department).readValue(updateJson);

        // Then
        Assertions.assertThat(updatedDepartment.getName()).isEqualTo("IT Department");
        Assertions.assertThat(updatedDepartment.getEmployees())
            .hasSize(4)
            .extracting("id", "name")
            .containsExactlyInAnyOrder(
                Tuple.tuple("1", "John"),
                Tuple.tuple("2", "Alice"), 
                Tuple.tuple("2", "Alice"), 
                Tuple.tuple("3", "Bob")
            );
    }
} 
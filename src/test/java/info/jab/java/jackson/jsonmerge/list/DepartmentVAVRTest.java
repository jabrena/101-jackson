package info.jab.java.jackson.jsonmerge.list;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import io.vavr.jackson.datatype.VavrModule.Settings;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;

class DepartmentVAVRTest {

    // Should be 3 employees, not 2. Pending conversation. (Immutable List)
    @Test
    public void should_MergeDepartmentEmployees_WhenUpdatingWithNewAndExistingEmployees() throws Exception {
        // Given
        DepartmentVAVR department = new DepartmentVAVR("IT", List.of(
            new Employee("1", "John"),
            new Employee("2", "Alice")
        ));

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

        ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDefaultPropertyInclusion(Include.NON_EMPTY)
            .registerModule(new VavrModule(new Settings().deserializeNullAsEmptyCollection(true)));

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
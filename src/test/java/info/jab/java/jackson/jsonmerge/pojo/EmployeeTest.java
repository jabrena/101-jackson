package info.jab.java.jackson.jsonmerge.pojo;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {

    @Test
    void should_update_employee_with_partial_json_data() throws JsonProcessingException {
        // Given
        String jsonToMerge = """
                {
                    "name": "Jake",
                    "salary": 3000,
                    "address": {
                        "street": "101 Blue Dr",
                        "city": "White Smoke"
                    }
                }""";
        Employee existingEmployee = new Employee("John", "Dev", 1000, "222-222-222",
                new Address("101 Blue Dr", "SunBridge", "23456"));

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(existingEmployee);
        Employee updatedEmployee = objectReader.readValue(jsonToMerge);

        // Then
        assertThat(updatedEmployee.getName()).isEqualTo("Jake");
        assertThat(updatedEmployee.getDept()).isEqualTo("Dev");  // unchanged
        assertThat(updatedEmployee.getSalary()).isEqualTo(3000);
        assertThat(updatedEmployee.getPhone()).isEqualTo("222-222-222");  // unchanged
        assertThat(updatedEmployee.getAddress().getStreet()).isEqualTo("101 Blue Dr");
        assertThat(updatedEmployee.getAddress().getCity()).isEqualTo("White Smoke");
        assertThat(updatedEmployee.getAddress().getZipCode()).isEqualTo("23456");  // unchanged
    }
} 

package info.jab.java.jackson.jsonmerge.pojo;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * For POJOs merging is done recursively, property by property.
 * 
 * 
 * These tests cover important edge cases in the merge process:
 * - Null handling
 * - Partial updates
 * - Nested object updates
 * - Missing property handling
 * - Complete object replacement
 * 
 * You could further expand this by testing:
 * - Invalid JSON formats
 * - Empty objects
 * - Array properties (if added to the POJO)
 * - Different data types (trying to set a number as a string, etc.)
 * - Very large objects
 * - Special characters in strings
 */
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

        Address address = new Address("101 Blue Dr", "SunBridge", "23456");
        Employee existingEmployee = new Employee("John", "Dev", 1000, "222-222-222", address);

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

    @Test
    void should_handle_null_values_in_merge() throws JsonProcessingException {
        // Given
        String jsonToMerge = """
                {
                    "name": null,
                    "salary": 2000,
                    "address": {
                        "street": null,
                        "city": "New City"
                    }
                }""";

        Address address = new Address("101 Blue Dr", "SunBridge", "23456");
        Employee existingEmployee = new Employee("John", "Dev", 1000, "222-222-222", address);
        
        // When
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(existingEmployee);
        Employee updatedEmployee = objectReader.readValue(jsonToMerge);

        // Then
        assertThat(updatedEmployee.getName()).isNull();
        assertThat(updatedEmployee.getDept()).isEqualTo("Dev");  // unchanged
        assertThat(updatedEmployee.getSalary()).isEqualTo(2000);
        assertThat(updatedEmployee.getAddress().getStreet()).isNull();
        assertThat(updatedEmployee.getAddress().getCity()).isEqualTo("New City");
    }

    @Test
    void should_handle_missing_properties_in_merge() throws JsonProcessingException {
        // Given
        String jsonToMerge = """
                {
                    "name": "Jake",
                    "address": {
                        "city": "New City"
                    }
                }""";

        Address address = new Address("101 Blue Dr", "SunBridge", "23456");
        Employee existingEmployee = new Employee("John", "Dev", 1000, "222-222-222", address);
        
        // When
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(existingEmployee);
        Employee updatedEmployee = objectReader.readValue(jsonToMerge);

        // Then
        assertThat(updatedEmployee.getName()).isEqualTo("Jake");
        assertThat(updatedEmployee.getSalary()).isEqualTo(1000);  // unchanged
        assertThat(updatedEmployee.getAddress().getStreet()).isEqualTo("101 Blue Dr");  // unchanged
        assertThat(updatedEmployee.getAddress().getCity()).isEqualTo("New City");
    }

    @Test
    void should_handle_complete_address_replacement() throws JsonProcessingException {
        // Given
        String jsonToMerge = """
                {
                    "address": null
                }""";

        Address address = new Address("101 Blue Dr", "SunBridge", "23456");
        Employee existingEmployee = new Employee("John", "Dev", 1000, "222-222-222", address);
        
        // When
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(existingEmployee);
        Employee updatedEmployee = objectReader.readValue(jsonToMerge);

        // Then
        assertThat(updatedEmployee.getAddress()).isNull();
        assertThat(updatedEmployee.getName()).isEqualTo("John");  // unchanged
        assertThat(updatedEmployee.getSalary()).isEqualTo(1000);  // unchanged
    }
} 

package info.jab.java.jackson.jsonmerge;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

public class DepartmentTest {

    @Disabled
    @Test
    public void testJsonMerge() throws Exception {
        // Create object mapper
        ObjectMapper mapper = new ObjectMapper();
        
        // Create initial department with some employees
        Department department = new Department("IT");
        department.addEmployee(new Employee("1", "John"));
        department.addEmployee(new Employee("2", "Alice"));
        
        // Convert to JSON
        String originalJson = mapper.writeValueAsString(department);
        System.out.println(originalJson);
        
        // Create update JSON with modified and new employees
        String updateJson = """
            {
                "name": "IT Department",
                "employees": [
                    {"id": "2", "name": "Alice"},
                    {"id": "3", "name": "Bob"}
                ]
            }
            """;
            
        // Use readerForUpdating to merge the JSON into existing object
        Department updatedDepartment = mapper.readerForUpdating(department)
                                           .readValue(updateJson);
        
        // Verify results
        assertEquals("IT Department", updatedDepartment.getName());
        assertEquals(3, updatedDepartment.getEmployees().size());
    }
} 
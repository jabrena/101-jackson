package info.jab.java.jackson.jsonmerge.mapdm;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NestedMapContainerTest {

    @Test
    void should_merge_nested_maps() throws Exception {
        // Given
        NestedMapContainer original = new NestedMapContainer();
        original.addNestedEntry("department1", "employee1", "John");
        original.addNestedEntry("department1", "employee2", "Jane");
        original.addNestedEntry("department2", "employee1", "Bob");

        String update = """
            {
                "nestedData": {
                    "department1": {
                        "employee3": "Alice"
                    },
                    "department2": {
                        "employee2": "Charlie"
                    }
                }
            }
            """;

        // When
        ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDefaultPropertyInclusion(Include.NON_EMPTY)
            .enable(SerializationFeature.INDENT_OUTPUT);

        String originalJson = objectMapper.writeValueAsString(original);
        NestedMapContainer merged = objectMapper.readerForUpdating(original).readValue(update);
        String mergedJson = objectMapper.writeValueAsString(merged);

        System.out.println(originalJson);
        System.out.println(mergedJson);

        // Then
        assertThat(merged.getNestedData())
                .hasSize(2)
                .satisfies(map -> {
                    assertThat(map.get("department1"))
                            .containsEntry("employee1", "John")
                            .containsEntry("employee2", "Jane")
                            .containsEntry("employee3", "Alice");
                    
                    assertThat(map.get("department2"))
                            .containsEntry("employee1", "Bob")
                            .containsEntry("employee2", "Charlie");
                });
    }
} 
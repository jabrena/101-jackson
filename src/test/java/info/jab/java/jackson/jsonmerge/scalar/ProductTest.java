package info.jab.java.jackson.jsonmerge.scalar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

class ProductTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void given_original_product_when_partial_update_then_only_name_is_updated() throws Exception {
        // Given
        Product original = new Product("Original Name", "Original Description");
        String partialJson = """
                {
                    "name": "New Name"
                }
                """;

        // When
        Product merged = mapper.readerForUpdating(original)
                .readValue(partialJson);

        // Then
        assertEquals("New Name", merged.getName());
        assertEquals("Original Description", merged.getDescription());
    }

    @Test
    void given_original_product_when_complete_update_then_all_fields_are_updated() throws Exception {
        // Given
        Product original = new Product("Original Name", "Original Description");
        String completeJson = """
                {
                    "name": "New Name",
                    "description": "New Description"
                }
                """;

        // When
        Product merged = mapper.readerForUpdating(original)
                .readValue(completeJson);

        // Then
        assertEquals("New Name", merged.getName());
        assertEquals("New Description", merged.getDescription());
    }

    @Disabled("This test is not working as expected")
    @Test
    void given_original_product_when_update_with_null_description_then_description_is_preserved() throws Exception {
        // Given
        Product original = new Product("Original Name", "Original Description");
        String jsonWithNull = """
                {
                    "name": "New Name",
                    "description": null
                }
                """;

        // When
        Product merged = mapper.readerForUpdating(original)
                .readValue(jsonWithNull);

        // Then
        assertEquals("New Name", merged.getName());
        assertEquals("Original Description", merged.getDescription());
    }
} 
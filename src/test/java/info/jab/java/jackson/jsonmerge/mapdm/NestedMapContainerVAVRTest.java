package info.jab.java.jackson.jsonmerge.mapdm;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import io.vavr.collection.Map;
import io.vavr.jackson.datatype.VavrModule;
import io.vavr.jackson.datatype.VavrModule.Settings;

import static org.assertj.core.api.Assertions.assertThat;

class NestedMapContainerVAVRTest {

    @Disabled("This test is not working as expected")
    @Test
    void should_merge_nested_maps() throws Exception {
        // Given
        NestedMapContainerVAVR original = new NestedMapContainerVAVR();
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
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new VavrModule(new Settings().deserializeNullAsEmptyCollection(true)));

        String originalJson = objectMapper.writeValueAsString(original);
        NestedMapContainerVAVR merged = objectMapper.readerForUpdating(original).readValue(update);
        String mergedJson = objectMapper.writeValueAsString(merged);

        System.out.println(originalJson);
        System.out.println(mergedJson);

        // Then
        Map<String, Map<String, String>> nestedData = merged.getNestedData();
        
        assertThat(nestedData.size()).isEqualTo(2);
        
        Map<String, String> department1 = nestedData.get("department1").get();
        assertThat(department1.get("employee1").get()).isEqualTo("John");
        assertThat(department1.get("employee2").get()).isEqualTo("Jane");
        assertThat(department1.get("employee3").get()).isEqualTo("Alice");
        
        Map<String, String> department2 = nestedData.get("department2").get();
        assertThat(department2.get("employee1").get()).isEqualTo("Bob");
        assertThat(department2.get("employee2").get()).isEqualTo("Charlie");
    }
} 
package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vavr.jackson.datatype.VavrModule;
import io.vavr.jackson.datatype.VavrModule.Settings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Looking at your test, I notice that the behavior difference between JDK Map and VAVR Map comes from how Jackson handles merging with these different map implementations.
 * The key difference is:
 * JDK Map's merge behavior preserves existing entries and adds new ones (union)
 * VAVR Map's merge behavior replaces the entire map with the new values (overwrite)
 * 
 * If you need map merging functionality, you'll need to handle it manually or consider using a different approach. Some alternatives:
 * - Use regular Java Map instead of VAVR Map if map merging is crucial
 * - Implement custom deserializer that handles map merging
 * - Merge the maps manually after deserialization
 * 
 * The VAVR Jackson module is primarily focused on serialization/deserialization of VAVR collections and doesn't include built-in map merging functionality like some other Jackson modules.
 */
class PersonVAVRTest {

    @Test
    void should_merge_contacts_map_when_updating_person() throws Exception {
        // Given
        PersonVAVR person = new PersonVAVR();
        person.setName("John Doe");
        person.putContact("email", "john@example.com");
        person.putContact("phone", "123-456-7890");

        String jsonToMerge = """
            {
                "name": "John Doe",
                "contacts": {
                    "phone": "999-999-9999",
                    "twitter": "@johndoe"
                }
            }
            """;

        // When
        ObjectMapper mapper = new ObjectMapper()
                                   .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                   .setDefaultPropertyInclusion(Include.NON_EMPTY)
                                   .registerModule(new VavrModule(new Settings().deserializeNullAsEmptyCollection(true)));
        
        PersonVAVR updatedPerson = mapper.readerForUpdating(person).readValue(jsonToMerge);
        System.out.println(updatedPerson);

        // Then
        assertThat(updatedPerson.getName()).isEqualTo("John Doe");
        assertThat(updatedPerson.getContacts()).hasSize(3);
        assertThat(updatedPerson.getContacts().get("email").get()).isEqualTo("john@example.com");
        assertThat(updatedPerson.getContacts().get("phone").get()).isEqualTo("999-999-9999");
        assertThat(updatedPerson.getContacts().get("twitter").get()).isEqualTo("@johndoe");
    }
} 
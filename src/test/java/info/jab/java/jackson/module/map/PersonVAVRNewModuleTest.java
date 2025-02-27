package info.jab.java.jackson.module.map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import info.jab.java.jackson.module.vavr.VavrModule2;
import info.jab.java.jackson.module.vavr.VavrModule2.Settings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonVAVRNewModuleTest {

    @Test
    void shouldDeserializePersonVAVRFromJSON() throws Exception {
        // Given
        String json = """
                {
                    "name": "John Doe",
                    "contacts": {
                        "email": "john.doe@example.com",
                        "phone": "123-456-7890",
                        "address": "123 Main St"
                    }
                }
                """;

        // Configure ObjectMapper with VavrModule2
        ObjectMapper objectMapper = new ObjectMapper()
        .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setDefaultPropertyInclusion(Include.NON_EMPTY)
        .registerModule(new VavrModule2(new Settings().deserializeNullAsEmptyCollection(true)));
  
        // When
        PersonVAVR person = objectMapper.readValue(json, PersonVAVR.class);

        // Then
        assertNotNull(person);
        assertEquals("John Doe", person.getName());
        assertEquals(3, person.getContacts().size());
        assertEquals("john.doe@example.com", person.getContacts().get("email").get());
        assertEquals("123-456-7890", person.getContacts().get("phone").get());
        assertEquals("123 Main St", person.getContacts().get("address").get());
    }
    
    @Test
    void shouldSerializeAndDeserializePersonVAVR() throws Exception {
        // Given
        PersonVAVR original = new PersonVAVR();
        original.setName("Jane Smith");
        original.putContact("email", "jane.smith@example.com");
        original.putContact("twitter", "@janesmith");
        
        // Configure ObjectMapper with VavrModule2
        ObjectMapper objectMapper = new ObjectMapper()
        .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setDefaultPropertyInclusion(Include.NON_EMPTY)
        .registerModule(new VavrModule2(new Settings().deserializeNullAsEmptyCollection(true)));
  
        // When
        String json = objectMapper.writeValueAsString(original);
        PersonVAVR deserialized = objectMapper.readValue(json, PersonVAVR.class);
        
        // Then
        assertEquals(original, deserialized);
        assertEquals("Jane Smith", deserialized.getName());
        assertEquals("jane.smith@example.com", deserialized.getContacts().get("email").get());
        assertEquals("@janesmith", deserialized.getContacts().get("twitter").get());
    }
} 
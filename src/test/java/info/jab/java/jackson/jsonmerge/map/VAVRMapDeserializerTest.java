package info.jab.java.jackson.jsonmerge.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.vavr.jackson.datatype.VavrModule;

public class VAVRMapDeserializerTest {
    
    @Test
    public void testPersonVAVRDeserialization() throws Exception {
        // Create an ObjectMapper with VAVR module
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new VavrModule())
                .build();
        
        // Create initial PersonVAVR object
        PersonVAVR person = new PersonVAVR();
        person.setName("John Doe");
        person.putContact("email", "john@example.com");
        person.putContact("phone", "123-456-7890");
        
        // Define the JSON to merge
        String jsonToMerge = "{ \"contacts\": { \"twitter\": \"@johndoe\", \"phone\": \"987-654-3210\" } }";
        
        // Perform the update
        PersonVAVR updatedPerson = mapper.readerForUpdating(person).readValue(jsonToMerge);
        
        // Verify the result
        assertEquals("John Doe", updatedPerson.getName());
        assertEquals(3, updatedPerson.getContacts().size());
        assertEquals("john@example.com", updatedPerson.getContacts().get("email").get());
        assertEquals("987-654-3210", updatedPerson.getContacts().get("phone").get());
        assertEquals("@johndoe", updatedPerson.getContacts().get("twitter").get());
    }
    
    @Test
    public void testProductDeserialization() throws Exception {
        // Create an ObjectMapper with VAVR module
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new VavrModule())
                .build();
        
        // Create initial Product object
        Product product = new Product();
        product.setName("Smartphone");
        product.setDescription("Latest model");
        product.setAttribute("weight", 150);
        product.setAttribute("storage", 128);
        
        // Define the JSON to merge
        String jsonToMerge = "{ \"attributes\": { \"ram\": 8, \"storage\": 256 } }";
        
        // Perform the update
        Product updatedProduct = mapper.readerForUpdating(product).readValue(jsonToMerge);
        
        // Verify the result
        assertEquals("Smartphone", updatedProduct.getName());
        assertEquals("Latest model", updatedProduct.getDescription());
        assertEquals(3, updatedProduct.getAttributes().size());
        assertEquals(Integer.valueOf(150), updatedProduct.getAttributes().get("weight").get());
        assertEquals(Integer.valueOf(256), updatedProduct.getAttributes().get("storage").get());
        assertEquals(Integer.valueOf(8), updatedProduct.getAttributes().get("ram").get());
    }
} 
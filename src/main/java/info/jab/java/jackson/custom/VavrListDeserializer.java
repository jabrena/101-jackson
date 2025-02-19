package info.jab.java.jackson.custom;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.vavr.collection.List;
import java.io.IOException;

// Custom deserializer for Vavr List
class VavrListDeserializer extends JsonDeserializer<List> {

     @Override 
     public List deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // Convert Java array to Vavr List
        Object[] array = ctxt.readValue(p, Object[].class);
        return List.of(array);
    }
}

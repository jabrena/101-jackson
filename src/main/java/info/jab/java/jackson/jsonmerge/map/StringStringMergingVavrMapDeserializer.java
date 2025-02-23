package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import java.io.IOException;
import java.util.Iterator;

public class StringStringMergingVavrMapDeserializer extends JsonDeserializer<Map<String, String>> {
    
    @Override
    public Map<String, String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        // Get the current value if we're updating
        Object currentValue = p.getCurrentValue();
        Map<String, String> existingMap = null;
        if (currentValue instanceof PersonVAVR) {
            existingMap = ((PersonVAVR) currentValue).getContacts();
        }
        
        Map<String, String> result = existingMap != null ? existingMap : HashMap.empty();
        
        // Process all fields in the new JSON
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode valueNode = node.get(fieldName);
                String value = valueNode.asText();
                result = result.put(fieldName, value);
            }
        }
        
        return result;
    }
} 
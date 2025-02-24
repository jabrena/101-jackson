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

public class PersonVAVRMapDeserializer extends JsonDeserializer<Map<String, String>> {
    
    @Override
    public Map<String, String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        // Get current map from the PersonVAVR instance
        Map<String, String> currentMap = HashMap.empty();
        Object currentValue = p.getCurrentValue();
        if (currentValue instanceof PersonVAVR) {
            PersonVAVR person = (PersonVAVR) currentValue;
            currentMap = person.getContacts() != null ? person.getContacts() : HashMap.empty();
        }
        
        // Create a mutable map to store all entries
        java.util.Map<String, String> mergedMap = new java.util.HashMap<>();
        
        // Add all entries from current map
        currentMap.forEach(mergedMap::put);
        
        // Add or update entries from the incoming JSON
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String key = fieldNames.next();
                String value = node.get(key).asText();
                mergedMap.put(key, value);
            }
        }
        
        // Convert back to VAVR Map
        return HashMap.ofAll(mergedMap);
    }
} 
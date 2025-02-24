package info.jab.java.jackson.jsonmerge.issue185;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import java.io.IOException;
import java.util.Iterator;

public class ParentVAVRDeepMapDeserializer extends JsonDeserializer<Map<String, Map<String, String>>> {
    
    @Override
    public Map<String, Map<String, String>> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        // Get current deepMap from the ParentVAVR instance
        Map<String, Map<String, String>> currentMap = HashMap.empty();
        Object currentValue = p.getCurrentValue();
        if (currentValue instanceof ParentVAVR) {
            ParentVAVR parent = (ParentVAVR) currentValue;
            currentMap = parent.deepMap != null ? parent.deepMap : HashMap.empty();
        }
        
        // Create a mutable map to store all entries
        java.util.Map<String, java.util.Map<String, String>> mergedMap = new java.util.HashMap<>();
        
        // Add all entries from current map
        currentMap.forEach((key, value) -> {
            java.util.Map<String, String> innerMap = new java.util.HashMap<>();
            value.forEach(innerMap::put);
            mergedMap.put(key, innerMap);
        });
        
        // Add or update entries from the incoming JSON
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String key = fieldNames.next();
                JsonNode innerNode = node.get(key);
                
                java.util.Map<String, String> innerMap = mergedMap.computeIfAbsent(key, k -> new java.util.HashMap<>());
                
                if (innerNode.isObject()) {
                    Iterator<String> innerFieldNames = innerNode.fieldNames();
                    while (innerFieldNames.hasNext()) {
                        String innerKey = innerFieldNames.next();
                        String value = innerNode.get(innerKey).asText();
                        innerMap.put(innerKey, value);
                    }
                }
            }
        }
        
        // Convert back to VAVR Map
        return HashMap.ofAll(mergedMap.entrySet().stream()
            .collect(java.util.stream.Collectors.toMap(
                java.util.Map.Entry::getKey,
                e -> HashMap.ofAll(e.getValue())
            )));
    }
} 
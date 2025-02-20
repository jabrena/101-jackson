package info.jab.java.jackson.jsonmerge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ObjectWithMapTest {

    @Test
    void givenAnObjectWithAMap_whenUsingJsonMerge_thenExpectAllFieldsInMap() throws JsonProcessingException {
        
        String newData = "{\"stringPairs\":{\"field1\":\"value1\",\"field2\":\"value2\"}}";
        
        Map<String, String> map = new HashMap<>();
        map.put("field3", "value3");
        ObjectWithMap objectToUpdateWith = new ObjectWithMap("James", map);
        
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWithMap update = objectMapper.readerForUpdating(objectToUpdateWith).readValue(newData);
    
        assertTrue(update.getStringPairs().containsKey("field1"));
        assertTrue(update.getStringPairs().containsKey("field2"));
        assertTrue(update.getStringPairs().containsKey("field3"));
    }

} 
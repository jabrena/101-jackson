package info.jab.java.jackson.custom;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.vavr.collection.List;

// Factory class to handle different list type deserializations
public class ListDeserializerFactory {
    private static final ObjectMapper javaListMapper = new ObjectMapper();
    private static final ObjectMapper vavrListMapper;
    
    static {
        vavrListMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new VavrListDeserializer());
        vavrListMapper.registerModule(module);
    }
    
    // Custom deserializer for Vavr List
    private static class VavrListDeserializer extends JsonDeserializer<List<?>> {
        @Override
        public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            Object[] array = ctxt.readValue(p, Object[].class);
            return List.of(array);
        }
    }
    
    // Generic method to deserialize based on list type
    public static <T extends Collection<String>> ChildrenVAVR deserialize(
            String json, 
            Class<T> listType) throws IOException {
        
        if (listType.equals(List.class)) {
            // Handle Vavr List
            ChildrenVAVR wrapper = vavrListMapper.readValue(
                json, 
                new TypeReference<ChildrenVAVR>() {}
            );
            return wrapper;
        } else {
            // Handle Java List
            return javaListMapper.readValue(
                json, 
                new TypeReference<ChildrenVAVR>() {}
            );
        }
    }
}

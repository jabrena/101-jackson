package info.jab.java.jackson.jsonmerge.vavr;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;

import java.io.IOException;
import java.util.function.Function;
import java.util.ArrayList;

/**
 * A generic deserializer for VAVR Lists that supports JSON merge.
 * This deserializer handles merging of lists instead of replacing them.
 * 
 * @param <T> The type of the parent/container object
 * @param <E> The type of elements in the list
 */
public class VavrJsonMergeListDeserializer<T, E> extends JsonDeserializer<List<E>> {
    
    private final Class<T> parentClass;
    private final Class<E> elementClass;
    private final Function<T, List<E>> listGetter;
    
    /**
     * Creates a new VavrJsonMergeListDeserializer.
     * 
     * @param parentClass The class of the parent/container object
     * @param elementClass The class of the elements in the list
     * @param listGetter Function to extract the current list from the parent object
     */
    public VavrJsonMergeListDeserializer(
        Class<T> parentClass, 
        Class<E> elementClass, 
        Function<T, List<E>> listGetter) {
        this.parentClass = parentClass;
        this.elementClass = elementClass;
        this.listGetter = listGetter;
    }
    
    @Override
    public List<E> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        // Get current list from the parent instance
        List<E> currentList = List.empty();
        Object currentValue = p.currentValue();
        
        if (parentClass.isInstance(currentValue)) {
            @SuppressWarnings("unchecked")
            T parent = (T) currentValue;
            currentList = listGetter.apply(parent);
            if (currentList == null) {
                currentList = List.empty();
            }
        }
        
        // Create a mutable list to store all entries
        java.util.List<E> mergedList = new ArrayList<>();
        
        // First, add all existing entries to preserve them
        mergedList.addAll(currentList.asJava());
        
        // Then add all new entries without replacing existing ones
        if (node.isArray()) {
            for (JsonNode itemNode : node) {
                E newItem = mapper.treeToValue(itemNode, elementClass);
                mergedList.add(newItem);
            }
        }
        
        // Convert back to VAVR List
        return List.ofAll(mergedList);
    }
} 
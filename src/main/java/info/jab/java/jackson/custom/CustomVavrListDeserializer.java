package info.jab.java.jackson.custom;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import java.io.IOException;

public class CustomVavrListDeserializer<T> extends JsonDeserializer<List<T>> {
    private final Class<T> elementType;

    public CustomVavrListDeserializer(Class<T> elementType) {
        this.elementType = elementType;
    }

    @Override
    public List<T> deserialize(JsonParser p, DeserializationContext ctxt) 
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        List<T> result = List.empty();
        if (node.isArray()) {
            for (JsonNode elementNode : node) {
                T element = mapper.treeToValue(elementNode, elementType);
                result = result.append(element);
            }
        }
        return result;
    }
} 
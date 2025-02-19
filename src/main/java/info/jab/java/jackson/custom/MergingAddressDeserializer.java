package info.jab.java.jackson.custom;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// Custom Deserializer for Address
@SuppressWarnings("rawtypes")
class MergingAddressDeserializer extends JsonDeserializer {
    
    @Override
    public Address deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    ObjectMapper mapper = (ObjectMapper) p.getCodec();
    JsonNode node = mapper.readTree(p);
        // Get current Address object if available
        Address current = (Address) ctxt.getAttribute("currentAddress");
        Address newAddress = mapper.treeToValue(node, Address.class);

        if (current != null) {
            if (newAddress.city != null) current.city = newAddress.city;
            if (newAddress.state != null) current.state = newAddress.state;
            return current;
        }
        return newAddress;
    }
}

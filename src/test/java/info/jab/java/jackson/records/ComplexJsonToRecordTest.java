package info.jab.java.jackson.records;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComplexJsonToRecordTest {
    
    @Test
    void given_complex_json_when_deserialized_then_record_is_created()  throws JsonProcessingException {
        String json = """
            {
                 "id": 1,
                 "firstName": "Ramesh",
                 "lastName": "Fadatare",
                 "email": "ramesh@example.com",
                 "address": {
                     "street": "Main street",
                     "city": "Pune",
                     "zipCode": "12345",
                     "country": "India"
                 }
             }
          """;

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);

        assertEquals(1, user.id());
        assertEquals("Ramesh", user.firstName());
        assertEquals("Fadatare", user.lastName());
        assertEquals("ramesh@example.com", user.email());
        assertEquals("Main street", user.address().street());
    }
}

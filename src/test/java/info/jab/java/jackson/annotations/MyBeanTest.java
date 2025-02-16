package info.jab.java.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyBeanTest {

    @Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException {

        MyBean bean = new MyBean(1, "My bean");

        String result = new ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(bean);
        System.out.println(result);
        
        assertThat(result).contains("My bean");
        assertThat(result).contains("1");
        
        // Assert that "name" appears before "id" in the JSON
        int nameIndex = result.indexOf("\"name\"");
        int idIndex = result.indexOf("\"id\"");
        assertThat(nameIndex).isGreaterThan(-1); // Verify name exists
        assertThat(idIndex).isGreaterThan(-1);   // Verify id exists
        assertThat(nameIndex).isLessThan(idIndex); // Verify name comes before id
    }
}

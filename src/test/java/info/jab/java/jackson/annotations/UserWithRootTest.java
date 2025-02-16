package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.assertj.core.api.Assertions.assertThat;

class UserWithRootTest {

    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect() throws Exception {
     
        UserWithRoot user = new UserWithRoot(1, "John");
    
        ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);
        System.out.println(result);
    
        assertThat(result).contains("John");
        assertThat(result).contains("user");
    }
} 
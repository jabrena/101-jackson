package info.jab.java.jackson.annotations;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void whenSerializingUsingJsonIgnoreType_thenCorrect()throws JsonProcessingException, ParseException {
     
        User.Name name = new User.Name("John", "Doe");
        User user = new User(1, name);
    
        String result = new ObjectMapper()
          .writeValueAsString(user);
    
        assertThat(result).contains("1");
        assertThat(result).doesNotContain("name");
        assertThat(result).doesNotContain("John");
    }


} 
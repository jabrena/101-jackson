package info.jab.java.jackson.annotations;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class UnwrappedUserTest {

    @Test
    public void whenSerializingUsingJsonUnwrapped_thenCorrect()
      throws JsonProcessingException, ParseException {
        UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
        UnwrappedUser user = new UnwrappedUser(1, name);
    
        String result = new ObjectMapper().writeValueAsString(user);
        
        assertThat(result).contains("John");
        assertThat(result).doesNotContain("name");
    }

} 
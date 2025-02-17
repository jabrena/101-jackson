package info.jab.java.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class PrivateBeanTest {

    @Test
    public void whenSerializingUsingJsonAutoDetect_thenCorrect() throws Exception {
     
        PrivateBean bean = new PrivateBean(1, "My bean");
    
        String result = new ObjectMapper()
          .writeValueAsString(bean);

        assertThat(result).contains("1");
        assertThat(result).contains("My bean");
    }

} 
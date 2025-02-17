package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;  

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BeanWithIgnoreTest {

    @Test
    public void whenSerializingUsingJsonIgnoreProperties_thenCorrect() throws Exception {
     
        BeanWithIgnore bean = new BeanWithIgnore(1, "My bean");
    
        String result = new ObjectMapper()
          .writeValueAsString(bean);
        
        assertThat(result).contains("My bean");
        assertThat(result).doesNotContain("id");
    }
} 
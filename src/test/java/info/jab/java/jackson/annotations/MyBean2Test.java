package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class MyBean2Test {

    @Test
    public void whenUsingJsonProperty_thenCorrect() throws Exception {
        MyBean2 bean = new MyBean2(1, "My bean");
    
        String result = new ObjectMapper().writeValueAsString(bean);
        
        assertThat(result).contains("My bean");
        assertThat(result).contains("1");
    
        MyBean2 resultBean = new ObjectMapper()
          .readerFor(MyBean2.class)
          .readValue(result);

        assertThat(resultBean.getTheName()).isEqualTo("My bean");
    }
} 
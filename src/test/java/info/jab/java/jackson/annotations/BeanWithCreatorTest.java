package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BeanWithCreatorTest {

    @Test
    public void whenDeserializingUsingJsonCreator_thenCorrect() throws Exception {
     
        String json = "{\"id\":1,\"theName\":\"My bean\"}";
    
        BeanWithCreator bean = new ObjectMapper()
          .readerFor(BeanWithCreator.class)
          .readValue(json);
        
        
        assertThat(bean.getName()).isEqualTo("My bean");
    }

}   
package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BeanWithInjectTest {

    @Test
    public void whenDeserializingUsingJsonInject_thenCorrect() throws Exception {
     
        String json = "{\"name\":\"My bean\"}";
        
        InjectableValues inject = new InjectableValues.Std()
          .addValue(int.class, 1);
        BeanWithInject bean = new ObjectMapper().reader(inject)
          .forType(BeanWithInject.class)
          .readValue(json);
        
        assertThat(bean.getName()).isEqualTo("My bean");
        assertThat(bean.getId()).isEqualTo(1);
    }

} 
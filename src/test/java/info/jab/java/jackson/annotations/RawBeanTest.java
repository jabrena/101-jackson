package info.jab.java.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class RawBeanTest {

    @Test
    public void whenSerializingUsingJsonRawValue_thenCorrect() throws Exception {
     
        RawBean bean = new RawBean("My bean", "{\"attr\":false}");
    
        String result = new ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(bean);

        System.out.println(result);

        assertThat(result).contains("My bean");
        assertThat(result).contains("{\"attr\":false}");
    }

} 
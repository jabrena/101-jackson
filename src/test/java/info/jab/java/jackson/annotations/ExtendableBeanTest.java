package info.jab.java.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtendableBeanTest {
    
    @Test
    public void whenSerializingUsingJsonAnyGetter_thenCorrect() throws Exception {
     
        ExtendableBean bean = new ExtendableBean("My bean");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");
    
        String result = new ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(bean);
        System.out.println(result);
     
        assertThat(result).contains("attr1");
        assertThat(result).contains("val1");
    }

}

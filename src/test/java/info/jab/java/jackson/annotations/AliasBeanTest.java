package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
public class AliasBeanTest {

    @Test
    public void whenDeserializingUsingJsonAlias_thenCorrect() throws Exception {
        String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";
        AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
        assertThat(aliasBean.getFirstName()).isEqualTo("John");
    }
} 
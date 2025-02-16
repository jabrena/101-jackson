package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class TypeEnumWithValueTest {

    @Test
    public void whenSerializingUsingJsonValue_thenCorrect() throws Exception {
     
        String enumAsString = new ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(TypeEnumWithValue.TYPE1);
    
        System.out.println(enumAsString);

        assertThat(enumAsString).isEqualTo("\"Type A\"");
    }
} 
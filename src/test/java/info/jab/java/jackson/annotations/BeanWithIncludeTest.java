package info.jab.java.jackson.annotations;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

class BeanWithIncludeTest {

    @Test
    public void whenSerializingUsingJsonIncludeProperties_thenCorrect() throws Exception {
        final BeanWithInclude bean = new BeanWithInclude(1, "My bean");
        final String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);
        assertThat(result).contains("My bean");
        assertThat(result).doesNotContain("id");
        assertThat(result).contains("name");
    }

} 
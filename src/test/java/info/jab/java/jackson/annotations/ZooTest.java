package info.jab.java.jackson.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class ZooTest {

    @Test
    public void whenSerializingPolymorphic_thenCorrect() throws Exception {
        Zoo.Dog dog = new Zoo.Dog("lacy");
        Zoo zoo = new Zoo(dog);
    
        String result = new ObjectMapper()
          .writeValueAsString(zoo);
    
        assertThat(result).contains("type");
        assertThat(result).contains("dog");
    }

    @Test
    public void whenDeserializingPolymorphic_thenCorrect() throws Exception {
        String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";

        Zoo zoo = new ObjectMapper()
        .readerFor(Zoo.class)
        .readValue(json);

        assertThat(zoo.animal.name).isEqualTo("lacy");
        assertThat(zoo.animal.getClass()).isEqualTo(Zoo.Cat.class);
    }
} 
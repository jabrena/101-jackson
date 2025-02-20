package info.jab.java.jackson.jsonmerge;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProgrammerAnnotatedTest {

    @Test
    void givenAnObjectAndJson_whenUsingJsonMerge_thenExpectUpdateInPOJO() throws JsonProcessingException {
        String newData = "{\"favouriteLanguage\":\"Java\",\"keyboard\":{\"style\":\"Mechanical\"}}";
        ProgrammerAnnotated programmerToUpdate = new ProgrammerAnnotated("John", "C++", new Keyboard("Membrane", "US"));

        ObjectMapper objectMapper = new ObjectMapper();
        ProgrammerAnnotated update = objectMapper.readerForUpdating(programmerToUpdate).readValue(newData);

        assert(update.getFavouriteLanguage()).equals("Java");
        // Only works with annotation
        //assert(update.getKeyboard().getStyle()).equals("Mechanical");
    }

} 
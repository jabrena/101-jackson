package info.jab.java.jackson.jsonmerge;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

class ProgrammerNotAnnotatedTest {

    @Test
    void givenAnObjectAndJson_whenNotUsingJsonMerge_thenExpectNoUpdateInPOJO() throws Exception {

        String newData = "{\"favouriteLanguage\":\"Java\",\"keyboard\":{\"style\":\"Mechanical\"}}";

        ProgrammerNotAnnotated programmerToUpdate = new ProgrammerNotAnnotated("John", "C++", new Keyboard("Membrane", "US"));

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(programmerToUpdate);
        ProgrammerNotAnnotated update = objectReader.readValue(newData);

        assert(update.getFavouriteLanguage()).equals("Java");
    }
} 
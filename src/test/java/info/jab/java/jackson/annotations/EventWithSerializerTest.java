package info.jab.java.jackson.annotations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper; 

import static org.assertj.core.api.Assertions.assertThat;

class EventWithSerializerTest {

    @Test
    public void whenSerializingUsingJsonSerialize_thenCorrect() throws Exception {
     
        SimpleDateFormat df  = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    
        String toParse = "20-12-2014 02:30:00";
        Date date = df.parse(toParse);

        EventWithSerializer bean = new EventWithSerializer("party", date);
    
        String result = new ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(bean);

        System.out.println(result);
        assertThat(result).contains(toParse);
    }

    @Test
    public void whenDeserializingUsingJsonDeserialize_thenCorrect() throws Exception {
    
        String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        EventWithSerializer event = new ObjectMapper()
        .readerFor(EventWithSerializer.class)
        .readValue(json);
        
        assertThat(df.format(event.eventDate)).isEqualTo("20-12-2014 02:30:00");
    }
} 

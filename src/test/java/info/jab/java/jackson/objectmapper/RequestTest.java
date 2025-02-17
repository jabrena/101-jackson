package info.jab.java.jackson.objectmapper;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class RequestTest {

    @Test
    void test1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        Request request = new Request();
        String carAsString = objectMapper.writeValueAsString(request);
        System.out.println(carAsString);
        // output: {"car":{"color":"yellow","type":"renault"},"datePurchased":"2016-07-03 11:43 AM CEST"}        
    }
} 

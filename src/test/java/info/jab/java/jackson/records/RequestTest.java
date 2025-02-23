package info.jab.java.jackson.records;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class RequestTest {

    @Test
    void test1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        Request request = new Request(new Car("yellow", "renault"), new Date());
        String carAsString = objectMapper.writeValueAsString(request);
        System.out.println(carAsString);
        // output: {"car":{"color":"yellow","type":"renault"},"datePurchased":"2016-07-03 11:43 AM CEST"}        
    }
} 

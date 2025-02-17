package info.jab.java.jackson.objectmapper;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

class CarTest {

    @Test
    void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        //objectMapper.writeValue(new File("target/car.json"), car);
        String carAsString = objectMapper.writeValueAsString(car);
        System.out.println(carAsString);
    }

    @Test
    void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{ \"color\" : \"Black\", \"brand\" : \"BMW\" }";
        Car car = objectMapper.readValue(json, Car.class);
        System.out.println(car);
    }

    @Test
    void test3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = objectMapper.readTree(json);
        String color = jsonNode.get("color").asText();
        System.out.println(color);
    }

    @Test
    void test4() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonCarArray = 
        "[{ \"color\" : \"Black\", \"brand\" : \"BMW\" }, { \"color\" : \"Red\", \"brand\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
        System.out.println(listCar);
    }

    @Test
    void test5() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{ \"color\" : \"Black\", \"brand\" : \"BMW\" }";
        Map<String, Object> map  = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
        System.out.println(map);
    }

    @Test
    void test6() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        
        String jsonString = "{ \"color\" : \"Black\", \"brand\" : \"Fiat\", \"year\" : \"1970\" }";

        Car car = objectMapper.readValue(jsonString, Car.class);
        System.out.println(car);

        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        String year = jsonNodeYear.asText();
        System.out.println(year);
    }

    @Test
    void test7() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        
        SimpleModule module = 
        new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Car.class, new CustomCarSerializer());
        objectMapper.registerModule(module);
        Car car = new Car("yellow", "renault");
        String carJson = objectMapper.writeValueAsString(car);
        System.out.println(carJson);
    }

    @Test
    void test8() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{ \"color\" : \"Black\", \"brand\" : \"BMW\" }";

        SimpleModule module =
        new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Car.class, new CustomCarDeserializer());
        objectMapper.registerModule(module);
        Car car = objectMapper.readValue(json, Car.class);
        System.out.println(car);
    }

    @Test
    void test9() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonCarArray = 
        "[{ \"color\" : \"Black\", \"brand\" : \"BMW\" }, { \"color\" : \"Red\", \"brand\" : \"FIAT\" }]";
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);
        System.out.println(cars);
    }
} 
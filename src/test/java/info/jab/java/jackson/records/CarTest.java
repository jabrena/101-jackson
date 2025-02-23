package info.jab.java.jackson.records;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CarTest {

    @Test
    void should_serialize_car_to_json() throws Exception {
        // Given
        Car car = new Car("yellow", "renault");

        // When 
        ObjectMapper objectMapper = new ObjectMapper();
        String carAsString = objectMapper.writeValueAsString(car);

        // Then
        assertThat(carAsString).isEqualTo("{\"color\":\"yellow\",\"brand\":\"renault\"}");
    }

    @Test
    void should_deserialize_json_to_car() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                {
                    "color": "Black",
                    "brand": "BMW"
                }
                """;

        // When
        Car car = objectMapper.readValue(json, Car.class);

        // Then
        assertThat(car)
            .hasFieldOrPropertyWithValue("color", "Black")
            .hasFieldOrPropertyWithValue("brand", "BMW");
    }

    @Test
    void should_read_json_node_value() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                {
                    "color": "Black",
                    "type": "FIAT"
                }
                """;

        // When
        JsonNode jsonNode = objectMapper.readTree(json);
        String color = jsonNode.get("color").asText();

        // Then
        assertThat(color).isEqualTo("Black");
    }

    @Test
    void should_deserialize_json_array_to_car_list() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = """
                [
                    {
                        "color": "Black",
                        "brand": "BMW"
                    },
                    {
                        "color": "Red",
                        "brand": "FIAT"
                    }
                ]
                """;

        // When
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});

        // Then
        assertThat(listCar)
            .hasSize(2)
            .extracting("color", "brand")
            .containsExactly(
                tuple("Black", "BMW"),
                tuple("Red", "FIAT")
            );
    }

    @Test
    void should_deserialize_json_to_map() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                {
                    "color": "Black",
                    "brand": "BMW"
                }
                """;

        // When
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});

        // Then
        assertThat(map)
            .hasSize(2)
            .containsEntry("color", "Black")
            .containsEntry("brand", "BMW");
    }

    @Test
    void should_handle_unknown_properties() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        String jsonString = """
                {
                    "color": "Black",
                    "brand": "Fiat",
                    "year": "1970"
                }
                """;

        // When
        Car car = objectMapper.readValue(jsonString, Car.class);
        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        String year = jsonNodeYear.asText();

        // Then
        assertThat(car)
            .hasFieldOrPropertyWithValue("color", "Black")
            .hasFieldOrPropertyWithValue("brand", "Fiat");
        assertThat(year).isEqualTo("1970");
    }

    @Test
    void should_use_custom_serializer() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = 
        new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Car.class, new CustomCarSerializer());
        objectMapper.registerModule(module);
        Car car = new Car("yellow", "renault");

        // When
        String carJson = objectMapper.writeValueAsString(car);

        // Then
        assertThat(carJson).isEqualTo("{\"color\":\"yellow\",\"brand\":\"renault\"}");
    }

    @Test
    void should_use_custom_deserializer() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                { "color" : "Black", "brand" : "BMW" }
                """;
        SimpleModule module =
        new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Car.class, new CustomCarDeserializer());
        objectMapper.registerModule(module);

        // When
        Car car = objectMapper.readValue(json, Car.class);

        // Then
        assertThat(car)
            .hasFieldOrPropertyWithValue("color", "Black")
            .hasFieldOrPropertyWithValue("brand", "BMW");
    }

    @Test
    void should_deserialize_json_array_to_car_array() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = """
                [
                    {
                        "color": "Black",
                        "brand": "BMW"
                    },
                    {
                        "color": "Red",
                        "brand": "FIAT"
                    }
                ]
                """;
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        // When
        Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);

        // Then
        assertThat(cars)
            .hasSize(2)
            .extracting("color", "brand")
            .containsExactly(
                tuple("Black", "BMW"),
                tuple("Red", "FIAT")
            );
    }
} 
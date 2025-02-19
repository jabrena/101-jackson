package info.jab.java.jackson.custom;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;
import io.vavr.jackson.datatype.VavrModule.Settings;

import java.io.File;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonDeserializationTest {
    
    @Test
    void testJsonDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Children children = objectMapper.readValue(new File("src/test/resources/children.json"), Children.class);
        assertThat(children.getChildren().size()).isEqualTo(2);
    }

    @Test
    void testJsonDeserializationWithCustomName() throws Exception {

        // Create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Deserialize JSON into POJO
        Children wrapper = mapper.readValue(new File("src/test/resources/children.json"), Children.class);
        
        // Get the Child object
        Child child = wrapper.getChildren().get(0);

        // Print the deserialized data
        System.out.println("Name: " + child.getName());
        System.out.println("Age: " + child.getAge());
        System.out.println("Favorite Fruits: " + child.getFavoriteFruits());

        // Alternative: Print the entire object using toString()
        System.out.println("\nComplete child object:");
        System.out.println(child.toString());    
    }

    @Test
    void testJsonDeserializationWithCustomName2() throws Exception {
        // Create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        
        // Register a custom deserializer that properly handles Child objects
        module.addDeserializer(List.class, new CustomVavrListDeserializer<>(Child.class));
        mapper.registerModule(module);

        // Deserialize JSON into POJO
        ChildrenVAVR wrapper = mapper.readValue(new File("src/test/resources/children.json"), ChildrenVAVR.class);   
        System.out.println(wrapper.toString());
        Child child = wrapper.getChildren().get(0);
        System.out.println(child.toString());
    }

    @Test
    void testJsonDeserializationWithCustomName3() throws Exception {

        // Create ObjectMapper instance
        //ObjectMapper mapper = new ObjectMapper();
        //SimpleModule module = new SimpleModule();
        //module.addDeserializer(List.class, new VavrListDeserializer());
        //mapper.registerModule(module);

        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                                   .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                   .setDefaultPropertyInclusion(Include.NON_EMPTY)
                                   .registerModule(new VavrModule(new Settings().deserializeNullAsEmptyCollection(true)));
        
        // Deserialize JSON into POJO
        ChildrenVAVR wrapper = mapper.readValue(new File("src/test/resources/children.json"), ChildrenVAVR.class);   
        System.out.println(wrapper.toString());
        Child child = wrapper.getChildren().get(0);
        System.out.println(child.toString());
    }

    @Disabled
    @Test
    void testJsonDeserializationWithCustomName4() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
            
        // Register Custom Deserializer
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Address.class, new MergingAddressDeserializer());
        objectMapper.registerModule(module);
    
        // Initial JSON
        String initialJson = "{ \"name\": \"John\", \"address\": { \"city\": \"New York\" } }";
        User user = objectMapper.readValue(initialJson, User.class);
    
        // Merge JSON
        String mergeJson = "{ \"address\": { \"state\": \"NY\" } }";
        DeserializationContext ctx = objectMapper.getDeserializationContext();
        //ctx.setAttribute("currentAddress", user.getAddress());
    
        User updatedUser = objectMapper.readerForUpdating(user).readValue(mergeJson);
    
        System.out.println(updatedUser);
        // Output: User{name='John', address=Address{city='New York', state='NY'}}

    }
}

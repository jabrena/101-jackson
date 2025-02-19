package info.jab.java.jackson.custom;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class ItemTest {

    @Test
    void test1() throws Exception {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        String serialized = new ObjectMapper().writeValueAsString(myItem);
        System.out.println(serialized);
    }
} 
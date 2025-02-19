package info.jab.java.jackson.custom;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// User class with merging enabled
class User2 {
    public String name;
    
    @JsonMerge
    @JsonDeserialize(using = MergingAddressDeserializer.class)
    public Address address;
    
    @Override
    public String toString() {
        return "User{name='" + name + "', address=" + address + "}";
    }   
}

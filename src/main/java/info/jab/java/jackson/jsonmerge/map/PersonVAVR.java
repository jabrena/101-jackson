package info.jab.java.jackson.jsonmerge.map;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonMerge;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

public class PersonVAVR {
    // Properties
    private String name;
    
    @JsonMerge
    private Map<String, String> contacts = HashMap.empty();

    // Constructors
    public PersonVAVR() {}

    public PersonVAVR(String name, Map<String, String> contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    // Getters and Setters  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVAVR that = (PersonVAVR) o;   
        return Objects.equals(name, that.name) && Objects.equals(contacts, that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contacts);
    }

    // toString
    @Override
    public String toString() {
        return "PersonWithContactsVAVR{" +
                "name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }   
} 
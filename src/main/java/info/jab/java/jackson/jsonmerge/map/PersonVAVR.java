package info.jab.java.jackson.jsonmerge.map;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import info.jab.java.jackson.jsonmerge.vavr.VAVRMapDeserializer;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

public class PersonVAVR {
    // Properties
    private String name;
    
    @JsonMerge
    @JsonDeserialize(using = PersonVAVRContactsDeserializer.class)
    private Map<String, String> contacts = HashMap.empty();
    
    // Static inner class for the specific deserializer
    private static class PersonVAVRContactsDeserializer extends VAVRMapDeserializer<PersonVAVR, String, String> {
        public PersonVAVRContactsDeserializer() {
            super(PersonVAVR.class, String.class, String.class, PersonVAVR::getContacts);
        }
    }

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

    // Helper method for immutable map operations
    public void putContact(String key, String value) {
        this.contacts = this.contacts.put(key, value);
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
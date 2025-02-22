package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.annotation.JsonMerge;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Person {
    // Properties
    private String name;

    @JsonMerge
    private Map<String, String> contacts = new HashMap<>();

    // Constructors
    public Person() {}

    public Person(String name, Map<String, String> contacts) {
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
        Person that = (Person) o;   
        return Objects.equals(name, that.name) && Objects.equals(contacts, that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contacts);
    }

    // toString
    @Override
    public String toString() {
        return "PersonWithContacts{" +
                "name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
    
} 
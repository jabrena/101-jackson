package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.annotation.JsonMerge;
import java.util.HashMap;
import java.util.Map;

public class PersonWithContacts {
    private String name;
    
    @JsonMerge
    private Map<String, String> contacts;

    public PersonWithContacts() {
        this.contacts = new HashMap<>();
    }

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
} 
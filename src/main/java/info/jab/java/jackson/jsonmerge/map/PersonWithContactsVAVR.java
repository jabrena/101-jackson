package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.annotation.JsonMerge;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

public class PersonWithContactsVAVR {
    private String name;
    
    @JsonMerge
    private Map<String, String> contacts;

    public PersonWithContactsVAVR() {
        this.contacts = HashMap.empty();
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
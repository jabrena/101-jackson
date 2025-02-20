package info.jab.java.jackson.jsonmerge;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonMerge;

class ObjectWithMap {

    String name;
    @JsonMerge
    Map<String, String> stringPairs;
    // Standard getters, setters and constructors

    public ObjectWithMap() {}

    public ObjectWithMap(String name, Map<String, String> stringPairs) {
        this.name = name;
        this.stringPairs = stringPairs;
    }

    public String getName() {
        return name;
    }
    
    public Map<String, String> getStringPairs() {
        return stringPairs;
    }
}

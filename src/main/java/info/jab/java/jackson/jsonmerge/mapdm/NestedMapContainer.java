package info.jab.java.jackson.jsonmerge.mapdm;

import com.fasterxml.jackson.annotation.JsonMerge;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NestedMapContainer {
    
    @JsonMerge
    private Map<String, Map<String, String>> nestedData = new HashMap<>();
    
    public NestedMapContainer() {
    }

    public NestedMapContainer(Map<String, Map<String, String>> nestedData) {
        this.nestedData = nestedData;
    }
    
    public Map<String, Map<String, String>> getNestedData() {
        return nestedData;
    }
    
    public void setNestedData(Map<String, Map<String, String>> nestedData) {
        this.nestedData = nestedData;
    }
    
    public void addNestedEntry(String outerKey, String innerKey, String value) {
        nestedData.computeIfAbsent(outerKey, k -> new HashMap<>())
                  .put(innerKey, value);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedMapContainer that = (NestedMapContainer) o;
        return Objects.equals(nestedData, that.nestedData);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nestedData);
    }
    
    @Override
    public String toString() {
        return "NestedMapContainer{" +
               "nestedData=" + nestedData +
               '}';
    }
} 
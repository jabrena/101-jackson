package info.jab.java.jackson.jsonmerge.mapdm;

import com.fasterxml.jackson.annotation.JsonMerge;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import java.util.Objects;

public class NestedMapContainerVAVR {
    
    @JsonMerge
    private Map<String, Map<String, String>> nestedData = HashMap.empty();
    
    public NestedMapContainerVAVR() {
    }
    
    public NestedMapContainerVAVR(Map<String, Map<String, String>> nestedData) {
        this.nestedData = nestedData;
    }
    
    public Map<String, Map<String, String>> getNestedData() {
        return nestedData;
    }
    
    public void setNestedData(Map<String, Map<String, String>> nestedData) {
        this.nestedData = nestedData;
    }
    
    public void addNestedEntry(String outerKey, String innerKey, String value) {
        Map<String, String> innerMap = nestedData.get(outerKey).getOrElse(HashMap.empty());
        Map<String, String> updatedInnerMap = innerMap.put(innerKey, value);
        this.nestedData = nestedData.put(outerKey, updatedInnerMap);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedMapContainerVAVR that = (NestedMapContainerVAVR) o;
        return Objects.equals(nestedData, that.nestedData);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nestedData);
    }
    
    @Override
    public String toString() {
        return "NestedMapContainerVAVR{" +
               "nestedData=" + nestedData +
               '}';
    }
} 
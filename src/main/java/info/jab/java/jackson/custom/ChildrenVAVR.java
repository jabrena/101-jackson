package info.jab.java.jackson.custom;

import io.vavr.collection.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildrenVAVR {
    @JsonProperty("children")
    private List<Child> children;

    // Default constructor needed for Jackson
    public ChildrenVAVR() {
    }

    public ChildrenVAVR(List<Child> children) {
        this.children = children;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}

package info.jab.java.jackson.custom;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Children {
    @JsonProperty("children")
    private List<Child> children;

    // Default constructor needed for Jackson
    public Children() {
    }

    public Children(List<Child> children) {
        this.children = children;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}

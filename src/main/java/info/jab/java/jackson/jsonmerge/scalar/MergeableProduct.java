package info.jab.java.jackson.jsonmerge.scalar;
import com.fasterxml.jackson.annotation.JsonMerge;

public class MergeableProduct {
    private String name;
    
    @JsonMerge
    private String description;
    
    // Constructors
    public MergeableProduct() {}
    
    public MergeableProduct(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
} 
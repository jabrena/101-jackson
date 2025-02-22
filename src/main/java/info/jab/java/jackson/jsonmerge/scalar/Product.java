package info.jab.java.jackson.jsonmerge.scalar;
import com.fasterxml.jackson.annotation.JsonMerge;
import java.util.Objects;

public class Product {
    // Properties
    private String name;
    
    @JsonMerge
    private String description;
    
    // Constructors
    public Product() {}
    
    public Product(String name, String description) {
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

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
               Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    // toString

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
} 
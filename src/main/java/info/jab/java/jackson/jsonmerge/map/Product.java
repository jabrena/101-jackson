package info.jab.java.jackson.jsonmerge.map;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import info.jab.java.jackson.jsonmerge.vavr.VavrJsonMergeMapDeserializer;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

/**
 * An example class that uses the generic VAVR Map deserializer
 * with different key/value types (String keys, Integer values).
 */
public class Product {
    private String name;
    private String description;
    
    @JsonMerge
    @JsonDeserialize(using = ProductAttributesDeserializer.class)
    private Map<String, Integer> attributes = HashMap.empty();
    
    // Static inner class for the specific deserializer
    private static class ProductAttributesDeserializer extends VavrJsonMergeMapDeserializer<Product, String, Integer> {
        public ProductAttributesDeserializer() {
            super(Product.class, String.class, Integer.class, Product::getAttributes);
        }
    }
    
    // Constructors
    public Product() {}
    
    public Product(String name, String description, Map<String, Integer> attributes) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
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
    
    public Map<String, Integer> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(Map<String, Integer> attributes) {
        this.attributes = attributes;
    }
    
    // Helper method for immutable map operations
    public void setAttribute(String key, Integer value) {
        this.attributes = this.attributes.put(key, value);
    }
    
    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
               Objects.equals(description, product.description) &&
               Objects.equals(attributes, product.attributes);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, description, attributes);
    }
    
    // toString
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attributes=" + attributes +
                '}';
    }
} 
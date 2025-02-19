package info.jab.java.jackson.custom;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Child {
    private String name;
    private int age;
    
    @JsonProperty("favorite_fruits")
    private List<String> favoriteFruits;
    
    // Default constructor needed for Jackson
    public Child() {
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public List<String> getFavoriteFruits() {
        return favoriteFruits;
    }
    
    public void setFavoriteFruits(List<String> favoriteFruits) {
        this.favoriteFruits = favoriteFruits;
    }
    
    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", favoriteFruits=" + favoriteFruits +
                '}';
    }
}

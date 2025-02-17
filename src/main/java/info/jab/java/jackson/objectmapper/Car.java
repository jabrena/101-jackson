package info.jab.java.jackson.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

    @JsonProperty
    private String color;
    
    @JsonProperty
    private String brand;

    public Car() {}

    public Car(String color, String brand) {
        this.color = color;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setColor(String color2) {
        this.color = color;
    }
}

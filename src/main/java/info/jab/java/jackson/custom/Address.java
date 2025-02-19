package info.jab.java.jackson.custom;

// Address class (to be merged instead of replaced)
public class Address {
    public String city;
    public String state;

    @Override
    public String toString() {
        return "Address{city='" + city + "', state='" + state + "'}";
    }
}

package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyBean2 {
    public int id;
    private String name;

    public MyBean2() { }

    public MyBean2(int i, String string) {
        this.id = i;
        this.name = string;
    }

    @JsonProperty("name")
    public void setTheName(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getTheName() {
        return name;
    }
}

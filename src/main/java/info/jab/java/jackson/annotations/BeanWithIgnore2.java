package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BeanWithIgnore2 {
    @JsonIgnore
    public int id;
    public String name;

    public BeanWithIgnore2(int i, String string) {
        this.id = i;
        this.name = string;
    }  
}

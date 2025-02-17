package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "id" })
public class BeanWithIgnore { 
    public int id;
    public String name;

    public BeanWithIgnore(int i, String string) {
        this.id = i;
        this.name = string;
    }  
}

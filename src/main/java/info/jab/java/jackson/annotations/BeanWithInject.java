package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {
    @JacksonInject
    public int id;
    
    public String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
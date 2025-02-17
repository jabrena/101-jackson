package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@JsonIncludeProperties({ "name" })
public class BeanWithInclude {

    public int id;
    public String name;

    public BeanWithInclude(int i, String string) {
        this.id = i;
        this.name = string;
    }
}

package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

//https://github.com/eugenp/tutorials/blob/master/jackson-simple/src/main/java/com/baeldung/jackson/annotation/MyBean.java
@JsonPropertyOrder({ "name", "id" })
@JsonInclude(Include.NON_NULL)
public class MyBean {
    public int id;
    private String name;

    public MyBean() { }

    public MyBean(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    @JsonGetter("name")
    public String getTheName() {
        return name;
    }

    @JsonSetter("name")
    public void setTheName(String name) {
        this.name = name;
    }
}

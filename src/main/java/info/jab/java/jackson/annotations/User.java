package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class User {

    public int id;
    public Name name;

    public User(int i, Name name2) {
        this.id = i;
        this.name = name2;
    }

    @JsonIgnoreType
    public static class Name {

        public String firstName;
        public String lastName;

        public Name(String string, String string2) {
            this.firstName = string;
            this.lastName = string2;
        }
    }
}

package info.jab.java.jackson.annotations;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UnwrappedUser {

    public int id;

    @JsonUnwrapped
    public Name name;

    public UnwrappedUser(int i, Name name2) {
        this.id = i;
        this.name = name2;
    }

    public static class Name {

        public String firstName;
        public String lastName;

        public Name(String string, String string2) {
            this.firstName = string;
            this.lastName = string2;
        }
    }
}

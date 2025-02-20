package info.jab.java.jackson.jsonmerge;

import com.fasterxml.jackson.annotation.JsonMerge;

class ProgrammerAnnotated {

    String name;
    String favouriteLanguage;

    @JsonMerge
    Keyboard keyboard;

    public ProgrammerAnnotated() {}

    public ProgrammerAnnotated(String name, String favouriteLanguage, Keyboard keyboard) {
        this.name = name;
        this.favouriteLanguage = favouriteLanguage;
        this.keyboard = keyboard;
    }

    // Standard getters, setters and constructors
    public Object getFavouriteLanguage() {
        return favouriteLanguage;
    }
    public Object getKeyboard() {
        return keyboard;
    }
}

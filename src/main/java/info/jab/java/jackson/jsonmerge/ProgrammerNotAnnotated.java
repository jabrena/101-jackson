package info.jab.java.jackson.jsonmerge;

class ProgrammerNotAnnotated {

    String name;
    String favouriteLanguage;
    Keyboard keyboard;

    public ProgrammerNotAnnotated() {}

    public ProgrammerNotAnnotated(String name, String favouriteLanguage, Keyboard keyboard) {
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

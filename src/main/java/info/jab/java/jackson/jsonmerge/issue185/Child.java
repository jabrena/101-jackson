package info.jab.java.jackson.jsonmerge.issue185;

import java.util.Objects;

public class Child {
    private String name;
    private String description;

    public Child() {
    }

    public Child(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(name, child.name) &&
                Objects.equals(description, child.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
} 
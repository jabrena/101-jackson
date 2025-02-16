package info.jab.java.jackson.annotations;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EventWithSerializer {
    public String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date eventDate;

    public EventWithSerializer() {
        super();
    }

    public EventWithSerializer(final String name, final Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }
}

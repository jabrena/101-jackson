package info.jab.java.jackson.annotations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventWithFormat {

    public String name;

    @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd-MM-yyyy hh:mm:ss")
    public Date eventDate;

    public EventWithFormat(String string, Date date) {
        this.name = string;
        this.eventDate = date;
    }
}

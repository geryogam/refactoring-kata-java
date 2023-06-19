package com.sipios.refactoring.model;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class Body {

    private Item[] items;
    private String type;
    private ZonedDateTime dateTime;

    public Body(Item[] is, String t, ZonedDateTime dateTime) {
        this.items = is;
        this.type = t;
        if (dateTime == null) {
          this.dateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        } else {
          this.dateTime = dateTime;
        }
    }

    public Item[] getItems() {
        return items;
    }

    public String getType() {
        return type;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}

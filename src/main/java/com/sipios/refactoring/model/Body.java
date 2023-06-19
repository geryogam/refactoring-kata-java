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

    public double getCustomerDiscount() {
        if (type.equals("STANDARD_CUSTOMER")) {
          return 1;
        } else if (type.equals("PREMIUM_CUSTOMER")) {
          return 0.9;
        } else if (type.equals("PLATINUM_CUSTOMER")) {
          return 0.5;
        }
        return 1;
    }

    public double getTotalPriceLimit() {
        if (type.equals("STANDARD_CUSTOMER")) {
          return 200;
        } else if (type.equals("PREMIUM_CUSTOMER")) {
          return 800;
        } else if (type.equals("PLATINUM_CUSTOMER")) {
          return 2000;
        }
        return 200;
    }
}

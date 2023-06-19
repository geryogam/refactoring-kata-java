package com.sipios.refactoring.controller;

import java.time.Month;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    @PostMapping
    public String getPrice(@RequestBody Body b) {
        double p = 0;
        double d;
        final ZonedDateTime dateTime = b.getDateTime();

        // Compute discount for customer
        if (b.getType().equals("STANDARD_CUSTOMER")) {
            d = 1;
        } else if (b.getType().equals("PREMIUM_CUSTOMER")) {
            d = 0.9;
        } else if (b.getType().equals("PLATINUM_CUSTOMER")) {
            d = 0.5;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // Compute total amount depending on the types and quantity of product and
        // if we are in winter or summer discounts periods
        if (
            !(
                dateTime.getDayOfMonth() < 15 &&
                dateTime.getDayOfMonth() > 5 &&
                dateTime.getMonth() == Month.JANUARY
            ) &&
            !(
                dateTime.getDayOfMonth() < 15 &&
                dateTime.getDayOfMonth() > 5 &&
                dateTime.getMonth() == Month.JUNE
            )
        ) {
            if (b.getItems() == null) {
                return "0";
            }

            for (int i = 0; i < b.getItems().length; i++) {
                Item it = b.getItems()[i];

                if (it.getType().equals("TSHIRT")) {
                    p += 30 * it.getNb() * d;
                } else if (it.getType().equals("DRESS")) {
                    p += 50 * it.getNb() * d;
                } else if (it.getType().equals("JACKET")) {
                    p += 100 * it.getNb() * d;
                }
                // else if (it.getType().equals("SWEATSHIRT")) {
                //     price += 80 * it.getNb();
                // }
            }
        } else {
            if (b.getItems() == null) {
                return "0";
            }

            for (int i = 0; i < b.getItems().length; i++) {
                Item it = b.getItems()[i];

                if (it.getType().equals("TSHIRT")) {
                    p += 30 * it.getNb() * d;
                } else if (it.getType().equals("DRESS")) {
                    p += 50 * it.getNb() * 0.8 * d;
                } else if (it.getType().equals("JACKET")) {
                    p += 100 * it.getNb() * 0.9 * d;
                }
                // else if (it.getType().equals("SWEATSHIRT")) {
                //     price += 80 * it.getNb();
                // }
            }
        }

        try {
            if (b.getType().equals("STANDARD_CUSTOMER")) {
                if (p > 200) {
                    throw new Exception("Price (" + p + ") is too high for standard customer");
                }
            } else if (b.getType().equals("PREMIUM_CUSTOMER")) {
                if (p > 800) {
                    throw new Exception("Price (" + p + ") is too high for premium customer");
                }
            } else if (b.getType().equals("PLATINUM_CUSTOMER")) {
                if (p > 2000) {
                    throw new Exception("Price (" + p + ") is too high for platinum customer");
                }
            } else {
                if (p > 200) {
                    throw new Exception("Price (" + p + ") is too high for standard customer");
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return String.valueOf(p);
    }
}

class Body {

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

class Item {

    private String type;
    private int nb;

    public Item(String type, int quantity) {
        this.type = type;
        this.nb = quantity;
    }

    public String getType() {
        return type;
    }

    public int getNb() {
        return nb;
    }
}

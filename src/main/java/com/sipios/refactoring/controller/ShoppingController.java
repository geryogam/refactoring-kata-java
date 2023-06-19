package com.sipios.refactoring.controller;

import java.time.Month;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sipios.refactoring.model.Body;
import com.sipios.refactoring.model.Item;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    @PostMapping
    public String getPrice(@RequestBody Body b) {
        double p = 0;
        final ZonedDateTime dateTime = b.getDateTime();
        if (
            isWinterDiscountPeriod(dateTime)
            || isSummerDiscountPeriod(dateTime)
        ) {
            for (Item item: b.getItems()) {
                p += item.getPrice() * item.getNb()
                    * item.getSeasonalDiscount() * b.getCustomerDiscount();
            }
        } else {
            for (Item item: b.getItems()) {
                p += item.getPrice() * item.getNb() * b.getCustomerDiscount();
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

    private boolean isWinterDiscountPeriod(ZonedDateTime dateTime) {
        return dateTime.getDayOfMonth() > 5 && dateTime.getDayOfMonth() < 15
          && dateTime.getMonth() == Month.JANUARY;
    }

    private boolean isSummerDiscountPeriod(ZonedDateTime dateTime) {
        return dateTime.getDayOfMonth() > 5 && dateTime.getDayOfMonth() < 15
          && dateTime.getMonth() == Month.JUNE;
    }
}

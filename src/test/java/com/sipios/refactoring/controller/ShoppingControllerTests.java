package com.sipios.refactoring.controller;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.model.Body;
import com.sipios.refactoring.model.Item;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Test
    void should_not_throw() {
        final Item[] items = new Item[] {};
        final String customer = "STANDARD_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 5, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertDoesNotThrow(() -> controller.getPrice(body));
    }

    @Test
    void should_be_equal_for_standard_customer() {
        final Item[] items = new Item[] {
            new Item("TSHIRT", 1),
            new Item("DRESS", 1),
            new Item("JACKET", 1)
        };
        final String customer = "STANDARD_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 5, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertEquals(controller.getPrice(body), "180.0");
    }

    @Test
    void should_be_equal_for_premium_customer() {
        final Item[] items = new Item[] {
            new Item("TSHIRT", 1),
            new Item("DRESS", 1),
            new Item("JACKET", 1)
        };
        final String customer = "PREMIUM_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 5, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertEquals(controller.getPrice(body), "162.0");
    }

    @Test
    void should_be_equal_for_platinum_customer() {
        final Item[] items = new Item[] {
            new Item("TSHIRT", 1),
            new Item("DRESS", 1),
            new Item("JACKET", 1)
        };
        final String customer = "PLATINUM_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 5, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertEquals(controller.getPrice(body), "90.0");
    }

    @Test
    void should_be_equal_for_standard_customer_during_discount_period() {
        final Item[] items = new Item[] {
            new Item("TSHIRT", 1),
            new Item("DRESS", 1),
            new Item("JACKET", 1)
        };
        final String customer = "STANDARD_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 6, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertEquals(controller.getPrice(body), "160.0");
    }

    @Test
    void should_be_equal_for_premium_customer_during_discount_period() {
        final Item[] items = new Item[] {
            new Item("TSHIRT", 1),
            new Item("DRESS", 1),
            new Item("JACKET", 1)
        };
        final String customer = "PREMIUM_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 6, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertEquals(controller.getPrice(body), "144.0");
    }

    @Test
    void should_be_equal_for_platinum_customer_during_discount_period() {
        final Item[] items = new Item[] {
            new Item("TSHIRT", 1),
            new Item("DRESS", 1),
            new Item("JACKET", 1)
        };
        final String customer = "PLATINUM_CUSTOMER";
        final ZonedDateTime dateTime = ZonedDateTime.of(
            2023, 1, 6, 0, 0, 0, 0, ZoneId.of("Europe/Paris")
        );
        final Body body = new Body(items, customer, dateTime);
        Assertions.assertEquals(controller.getPrice(body), "80.0");
    }
}

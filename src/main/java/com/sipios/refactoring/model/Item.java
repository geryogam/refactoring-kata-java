package com.sipios.refactoring.model;

public class Item {

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

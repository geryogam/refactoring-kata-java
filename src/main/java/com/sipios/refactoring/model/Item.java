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

    public double getPrice() {
        if (type.equals("TSHIRT")) {
          return 30;
        } else if (type.equals("DRESS")) {
          return 50;
        } else if (type.equals("JACKET")) {
          return 100;
        }
        return 0;
    }

    public double getSeasonalDiscount() {
        if (type.equals("TSHIRT")) {
          return 1;
        } else if (type.equals("DRESS")) {
          return 0.8;
        } else if (type.equals("JACKET")) {
          return 0.9;
        }
        return 1;
    }
}

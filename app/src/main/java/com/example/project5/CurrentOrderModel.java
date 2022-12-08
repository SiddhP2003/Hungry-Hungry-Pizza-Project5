package com.example.project5;

public class CurrentOrderModel {
    String flavor;
    String styleAndCrust;
    String toppings;
    String size;
    String price;


    public CurrentOrderModel(String flavor, String styleAndCrust, String toppings, String size, String price) {
        this.flavor = flavor;
        this.styleAndCrust = styleAndCrust;
        this.toppings = toppings;
        this.size = size;
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getStyleAndCrust() {
        return styleAndCrust;
    }

    public String getToppings() {
        return toppings;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }
}

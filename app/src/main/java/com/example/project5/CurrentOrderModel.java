package com.example.project5;

public class CurrentOrderModel {
    String flavor;
    String styleAndCrust;
    String toppings;
    String size;
    String price;

    /**
     * Creates an object of CurrentOrderModel
     * @param flavor Flavor of the pizza.
     * @param styleAndCrust Style and crust of pizza.
     * @param toppings Selected pizza toppings
     * @param size Pizza size
     * @param price Price of the pizza
     */
    public CurrentOrderModel(String flavor, String styleAndCrust, String toppings, String size, String price) {
        this.flavor = flavor;
        this.styleAndCrust = styleAndCrust;
        this.toppings = toppings;
        this.size = size;
        this.price = price;
    }

    /**
     * Returns the pizza flavor
     * @return String containing flavor
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Returns the pizza style and crust.
     * @return String containing pizza style and crust.
     */
    public String getStyleAndCrust() {
        return styleAndCrust;
    }

    /**
     * Returns pizza toppings.
     * @return String containing all selected pizza toppings.
     */
    public String getToppings() {
        return toppings;
    }

    /**
     * Returns pizza size.
     * @return String containing the pizza size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Returns pizza price.
     * @return String containing the pizza price.
     */
    public String getPrice() {
        return price;
    }
}

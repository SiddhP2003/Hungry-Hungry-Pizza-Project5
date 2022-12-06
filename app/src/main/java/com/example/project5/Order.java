package com.example.project5;


import java.util.ArrayList;

/**
 A class that acts to outline a pizza order that can be made.
 Implements Customizable, such that a pizza could be removed from the order. It also outlines the
 pizzas that are part of the order, as well as the pricing of the order.
 @author Siddh Parmar, Yash Patel
 */
public class Order implements Customizable {
    private int number;
    private ArrayList<Pizza> pizzas;
    private final double TAXRATE = 0.06625;
    private double subTotal;
    private double salesTax;
    private double orderTotal;

    /**
     * Constructor for a current order
     */
    public Order(){
        pizzas = new ArrayList<Pizza>();
    }

    /**
     * Adds a pizza to the current orders
     * @param obj, the pizza to be added to the order
     * @return boolean, true if pizza was added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Pizza){
            pizzas.add((Pizza)obj);
        }
        return false;
    }

    /**
     * Removes a pizza to the current orders
     * @param obj, the pizza to be added to the order
     * @return boolean, true if pizza was removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Pizza){
            pizzas.remove((Pizza)obj);
        }
        return false;
    }

    /**
     * Returns the pizzas that have been placed into the current order
     * @return ArrayList<Pizza>, the pizzas that are part of the current order
     */
    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }

    /**
     * Returns the subtotal of the current order
     * @return double, the subtotal of the order
     */
    public double getSubTotal(){
        subTotal = 0;
        for(int i = 0; i < pizzas.size(); i++){
            subTotal += pizzas.get(i).price();
        }
        return subTotal;
    }

    /**
     * Returns the sales tax applied to the current order
     * @return double, the sales tax of the order
     */
    public double getSalesTax() {
        salesTax = 0;
        salesTax = subTotal * TAXRATE;
        return salesTax;
    }

    /**
     * Returns the total cost of the current order
     * @return double, the total cost of the order
     */
    public double getOrderTotal(){
        orderTotal = 0;
        orderTotal = subTotal + salesTax;
        return orderTotal;
    }

    /**
     * Returns the Pizza given the correlating name
     * @param pizza, the name of the pizza
     * @return Pizza, the pizza associated with the given name
     */
    public Pizza getPizza(String pizza){
        for(int i = 0; i < pizzas.size(); i++){
            if(pizzas.get(i).printPizza().equals(pizza)){
                return pizzas.get(i);
            }
        }
        return null;
    }

    /**
     * Sets the order number of the order
     * @parqm orderNumber, the order number of the current order
     */
    public void setOrderNumber(int orderNumber){
        this.number = orderNumber;
    }

    /**
     * Returns the order number
     * @return int, the order number
     */
    public int getNumber(){
        return number;
    }

}
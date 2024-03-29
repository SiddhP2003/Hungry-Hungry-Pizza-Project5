package com.example.project5;



import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 A class that acts to outline a store order for the pizzeria.
 Implements Customizable, such that an individual order can be removed from the Store orders. It
 also outlines the various individual orders that make up the store orders, and provides the
 ability to export the orders.
 @author Siddh Parmar, Yash Patel
 */
public class StoreOrder implements Customizable {
    private ArrayList<Order> orders;

    /**
     * Constructor for Store orders
     */
    public StoreOrder(){
        orders = new ArrayList<Order>();
    }

    /**
     * Adds a pizza order to the Store orders
     * @param obj, the current order
     * @return boolean, true if order added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            orders.add((Order)obj);
            return true;
        }
        return false;
    }

    /**
     * Removes a pizza order from the Store orders
     * @param obj, the current order
     * @return boolean, true if order removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            orders.remove((Order)obj);
            return true;
        }
        return false;
    }

    /**
     * Returns the various orders made in the pizzeria
     * @return ArrayList<Order>, the orders that have been placed
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }
}

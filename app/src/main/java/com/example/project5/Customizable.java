package com.example.project5;

/**
 An interface that outlines the ability to customize a pizza, and current or store orders.
 Outlines that a pizza can be customized by adding or removing toppings. Outlines that a current
 order can be customized by removing a pizza, or that a store order can be customized by removing
 an order.
 @author Siddh Parmar, Yash Patel
 */
public interface Customizable {
    /**
     * Add an object
     * @return boolean, true if added, false otherwise
     */
    boolean add(Object obj);

    /**
     * Remove an object
     * @return boolean, true if removed, false otherwise
     */
    boolean remove(Object obj);
}

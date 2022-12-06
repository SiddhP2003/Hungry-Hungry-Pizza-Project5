package com.example.project5;



import java.util.ArrayList;

/**
 An abstract class that is outlines the general things that a pizza consists of.
 Provides that a pizza will have a crust and size associated with it, as well as specific toppings.
 @author Siddh Parmar, Yash Patel
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Returns the price of the pizza
     * @return double, the price of the pizza
     */
    public abstract double price();

    /**
     * Constructor for a pizza
     * @param crust, the type of crust
     * @param size, the size of the pizza (small, medium, large)
     */
    public Pizza(Crust crust, Size size){
        toppings = new ArrayList<Topping>();
        this.crust = crust;
        this.size = size;
    }

    /**
     * Returns the toppings put on the pizza
     * @return ArrayList<Topping>, the toppings on the pizza
     */
    public ArrayList<Topping> getToppings(){
        return toppings;
    }

    /**
     * Returns the type of crust used
     * @return Crust, the type of crust
     */
    public Crust getCrust(){
        return crust;
    }

    /**
     * Returns the size of the pizza
     * @return Size, the size of the pizza
     */
    public Size getSize(){
        return size;
    }

    /**
     * Sets the size of the pizza
     * @param size, the size of the pizza
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * Returns the various aspects of the pizza
     * @return String, information regarding the pizza
     */
    public abstract String printPizza();


}

package com.example.project5;



import java.text.DecimalFormat;

/**
 A class that acts to represent a Meatzza Pizza
 Extends the Pizza class, meaning that the crust and size of the pizza are given as parameters in
 the constructor. The price of the Meatzza Pizza is set since no additional toppings can be
 added.
 @author Siddh Parmar, Yash Patel
 */
public class Meatzza extends Pizza{

    /**
     * Constructor for Meatzza Pizza
     * @param crust, the type of crust based on the pizza style
     * @param size, the size of the pizza (small, medium, large)
     */
    public Meatzza(Crust crust, Size size) {
        super(crust, size);
    }

    /**
     * Returns the various aspects of the Meatzza pizza.
     * @return String, the flavor, style, crust, toppings, size, and price of the pizza
     */
    @Override
    public String printPizza() {
        DecimalFormat format = new DecimalFormat("#.##");
        String style = "";
        if(this.getCrust().equals(Crust.STUFFED)){
            style = "Chicago Style";
        }
        else if(this.getCrust().equals(Crust.HANDTOSSED)){
            style = "New York Style";
        }
        String currentPizza = "Meatzza"+" " +
                "("+style+
                " - " + this.getCrust() + ")" + ",";
        for(int i = 0; i < this.getToppings().size(); i++){
            currentPizza = currentPizza.concat(this.getToppings().get(i) + ",");
        }
        currentPizza =
                currentPizza.concat(this.getSize().name()+","+"$"+format.format(this.price()));
        return currentPizza;
    }
    @Override
    public String getStyle(){
        if(this.getCrust().equals(Crust.STUFFED)){
            return "Chicago Style";
        }
        else {
            return "New York Style";
        }
    }
    /**
     * Returns the price of the pizza depending on the size
     * @return double, one of three prices based on pizza size
     */
    @Override
    public double price() {
        if(this.getSize() == Size.SMALL){
            return 15.99;
        }
        else if(this.getSize() == Size.MEDIUM){
            return 17.99;
        }
        else if(this.getSize() == Size.LARGE){
            return 19.99;
        }
        return 0;
    }

    /**
     * Adds a topping to the pizza
     * @return boolean, true if topping is added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Topping){
            this.getToppings().add((Topping)obj);
            return true;
        }
        return false;
    }

    /**
     * Removes a topping from the pizza
     * @return boolean, true if topping is removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Topping){
            this.getToppings().remove((Topping)obj);
            return true;
        }
        return false;
    }

    @Override
    public String getFlavor() {
        return "Meatzza";
    }
}


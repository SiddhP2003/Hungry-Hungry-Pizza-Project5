package com.example.project5;



import java.text.DecimalFormat;

/**
 A class that acts to represent a BBQ Chicken Pizza
 Extends the Pizza class, meaning that the crust and size of the pizza are given as parameters in
 the constructor. The price of the BBQ Chicken Pizza is set since no additional toppings can be
 added.
 @author Siddh Parmar, Yash Patel
 */
public class BBQChicken extends Pizza {

    /**
     * Constructor for BBQ Chicken Pizza
     * @param crust, the type of crust based on the pizza style
     * @param size, the size of the pizza (small, medium, large)
     */
    public BBQChicken(Crust crust, Size size) {
        super(crust, size);
    }

    /**
     * Returns the various aspects of the BBQ Chicken pizza.
     * @return String, the flavor, style, crust, toppings, size, and price of the pizza
     */
    @Override
    public String printPizza() {
        DecimalFormat format = new DecimalFormat("#.##");
        String style = "";
        if(this.getCrust().equals(Crust.PAN)){
            style = "Chicago Style";
        }
        else if(this.getCrust().equals(Crust.THIN)){
            style = "New York Style";
        }

        String currentPizza = "BBQ Chicken"+" " +
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
        if(this.getCrust().equals(Crust.PAN)){
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
            return 13.99;
        }
        else if(this.getSize() == Size.MEDIUM){
            return 15.99;
        }
        else if(this.getSize() == Size.LARGE){
            return 17.99;
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
        return "BBQChicken";
    }
}

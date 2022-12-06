package com.example.project5;



import java.text.DecimalFormat;

/**
 A class that acts to represent a Build Your Own Pizza
 Extends the Pizza class, meaning that the crust and size of the pizza are given as parameters in
 the constructor. There is an initial price of the Build Your Own Pizza depending on the size,
 and then every topping added will increase the price.
 @author Siddh Parmar, Yash Patel
 */
public class BuildYourOwn extends Pizza{

    /**
     * Constructor for Build Your Own Pizza
     * @param crust, the type of crust based on the pizza style
     * @param size, the size of the pizza (small, medium, large)
     */
    public BuildYourOwn(Crust crust, Size size) {
        super(crust, size);
    }

    /**
     * Returns the various aspects of the Build Your Own pizza.
     * @return String, the flavor, style, crust, toppings, size, and price of the pizza
     */
    @Override
    public String printPizza() {
        DecimalFormat format = new DecimalFormat("#.##");
        String style = "";
        if(this.getCrust().equals(Crust.PAN)){
            style = "Chicago Style";
        }
        else if(this.getCrust().equals(Crust.HANDTOSSED)){
            style = "New York Style";
        }

        String currentPizza = "Build Your Own"+" " +
                "("+style+
                " - " + this.getCrust() + ")" + ",";
        for(int i = 0; i < this.getToppings().size(); i++){
            currentPizza = currentPizza.concat(this.getToppings().get(i) + ",");
        }
        currentPizza =
                currentPizza.concat(this.getSize().name()+","+"$"+format.format(this.price()));
        return currentPizza;
    }


    /**
     * Returns the price of the pizza depending on the size
     * @return double, one of three prices based on pizza size
     */
    @Override
    public double price() {
        double price = 0;
        if(this.getSize() == Size.SMALL){
            price = 8.99;
        }
        else if(this.getSize() == Size.MEDIUM){
            price = 10.99;
        }
        else if(this.getSize() == Size.LARGE){
            price = 12.99;
        }
        price += this.getToppings().size() * 1.59;
        return price;
    }

    /**
     * Adds a topping to the pizza
     * @return boolean, true if the object is an instance of Topping, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(this.getToppings().size() == 7){
            return false;
        }
        else if(obj instanceof Topping){
            this.getToppings().add((Topping)obj);
            return true;
        }
        return false;
    }

    /**
     * Removes a topping from the pizza
     * @return boolean, true if the object is an instance of Topping, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Topping){
            this.getToppings().remove((Topping)obj);
            return true;
        }
        return false;
    }
}

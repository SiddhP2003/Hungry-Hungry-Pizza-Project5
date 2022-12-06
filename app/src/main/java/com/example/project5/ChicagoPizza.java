package com.example.project5;


/**
 A class that acts to represent Chicago-style pizzas.
 Implements PizzaFactory, and so it sets up the different types of Chicago-style pizzas with
 their respective toppings.
 @author Siddh Parmar, Yash Patel
 */
public class ChicagoPizza implements PizzaFactory{
    /**
     * Creates a deluxe Chicago-style pizza with its corresponding toppings
     * @return Pizza, small deluxe Chicago-style pizza
     */
    @Override
    public Pizza createDeluxe() {
        Deluxe deluxe = new Deluxe(Crust.DEEPDISH, Size.SMALL);
        deluxe.add(Topping.SAUSAGE);
        deluxe.add(Topping.PEPPERONI);
        deluxe.add(Topping.GREENPEPPER);
        deluxe.add(Topping.ONION);
        deluxe.add(Topping.MUSHROOM);
        return deluxe;
    }

    /**
     * Creates a BBQ Chicken Chicago-style pizza with its corresponding toppings
     * @return Pizza, small BBQ Chicken Chicago-style pizza
     */
    @Override
    public Pizza createBBQChicken() {
        BBQChicken bbqChicken = new BBQChicken(Crust.PAN, Size.SMALL);
        bbqChicken.add(Topping.BBQChicken);
        bbqChicken.add(Topping.GREENPEPPER);
        bbqChicken.add(Topping.PROVOLONE);
        bbqChicken.add(Topping.CHEDDAR);
        return bbqChicken;
    }

    /**
     * Creates a Meatzza Chicago-style pizza with its corresponding toppings
     * @return Pizza, small Meatzza Chicago-style pizza
     */
    @Override
    public Pizza createMeatzza() {
        Meatzza meatzza = new Meatzza(Crust.STUFFED, Size.SMALL);
        meatzza.add(Topping.SAUSAGE);
        meatzza.add(Topping.PEPPERONI);
        meatzza.add(Topping.BEEF);
        meatzza.add(Topping.HAM);
        return meatzza;
    }

    /**
     * Creates a Build Your Own Chicago-style pizza
     * @return Pizza, small Build Your Own Chicago-style pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN, Size.SMALL);
    }
}

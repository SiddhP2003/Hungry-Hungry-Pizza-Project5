package com.example.project5;



/**
 A class that acts to represent New York-style pizzas.
 Implements PizzaFactory, and so it sets up the different types of New York-style pizzas with
 their respective toppings.
 @author Siddh Parmar, Yash Patel
 */
public class NYPizza implements PizzaFactory {
    /**
     * Creates a deluxe New York-style pizza with its corresponding toppings
     * @return Pizza, small deluxe New York-style pizza
     */
    @Override
    public Pizza createDeluxe() {
        Deluxe deluxe = new Deluxe(Crust.BROOKLYN, Size.SMALL);
        deluxe.add(Topping.SAUSAGE);
        deluxe.add(Topping.PEPPERONI);
        deluxe.add(Topping.GREENPEPPER);
        deluxe.add(Topping.ONION);
        deluxe.add(Topping.MUSHROOM);
        return deluxe;
    }

    /**
     * Creates a BBQ Chicken New York-style pizza with its corresponding toppings
     * @return Pizza, small BBQ Chicken New York-style pizza
     */
    @Override
    public Pizza createBBQChicken() {
        BBQChicken bbqChicken = new BBQChicken(Crust.THIN, Size.SMALL);
        bbqChicken.add(Topping.BBQChicken);
        bbqChicken.add(Topping.GREENPEPPER);
        bbqChicken.add(Topping.PROVOLONE);
        bbqChicken.add(Topping.CHEDDAR);
        return bbqChicken;
    }

    /**
     * Creates a Meatzza New York-style pizza with its corresponding toppings
     * @return Pizza, small Meatzza New York-style pizza
     */
    @Override
    public Pizza createMeatzza() {
        Meatzza meatzza = new Meatzza(Crust.HANDTOSSED, Size.SMALL);
        meatzza.add(Topping.SAUSAGE);
        meatzza.add(Topping.PEPPERONI);
        meatzza.add(Topping.BEEF);
        meatzza.add(Topping.HAM);
        return meatzza;
    }

    /**
     * Creates a Build Your Own New York-style pizza
     * @return Pizza, small Build Your Own New York-style pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HANDTOSSED, Size.SMALL);
    }
}


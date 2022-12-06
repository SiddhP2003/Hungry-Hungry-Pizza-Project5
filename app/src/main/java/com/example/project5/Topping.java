package com.example.project5;



/**
 An enum class that outlines the various toppings that are available.
 This class outlines that there are various toppings available that can be put on a pizza.
 @author Siddh Parmar, Yash Patel
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREENPEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    BBQChicken("BBQ Chicken"),
    CHEDDAR("Cheddar"),
    BEEF("Beef"),
    HAM("Ham"),
    PROVOLONE("Provolone"),
    PINEAPPLE("Pineapple"),
    BLACKOLIVES("Black Olives"),
    JALAPENOPEPPERS("Jalapeno Peppers");

    private String topping;

    /**
     * Constructor for a topping
     * @param topping, the name of the topping
     */
    Topping(String topping) {
        this.topping = topping;
    }

    /**
     * Returns the name of the topping
     * @return String, the name of the topping
     */
    public String topping(){
        return topping;
    }
}

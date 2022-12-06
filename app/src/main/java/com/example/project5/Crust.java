package com.example.project5;


/**
 An enum class that outlines the various types of crust that are available.
 Based on the style and flavor of pizza, a certain crust will be used. This class acts to contain
 all the different types of crust available.
 @author Siddh Parmar, Yash Patel
 */
public enum Crust {
    DEEPDISH("Deep Dish"),
    PAN("Pan"),
    STUFFED("Stuffed"),
    BROOKLYN("Brooklyn"),
    THIN("Thin"),
    HANDTOSSED("Hand tossed");

    private String crust;

    /**
     * Constructor for crust that sets the type of crust
     * @param crust, the name of the crust
     */
    Crust(String crust) {
        this.crust = crust;
    }

    /**
     * Returns the crust
     * @return String, the name of the crust
     */
    public String crust(){
        return crust;
    }
}

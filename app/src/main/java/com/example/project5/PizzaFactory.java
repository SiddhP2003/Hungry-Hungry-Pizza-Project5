package com.example.project5;


/**
 An interface that outlines the various flavors of pizza that are available.
 Outlines that a pizza can be one of four specific flavors.
 @author Siddh Parmar, Yash Patel
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza
     * @return Pizza, Deluxe pizza
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza
     * @return Pizza, Meazzaeluxe pizza
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza
     * @return Pizza, BBQ Chicken pizza
     */
    Pizza createBBQChicken();

    /**
     * Creates a Build Your Own pizza
     * @return Pizza, Build Your Own pizza
     */
    Pizza createBuildYourOwn();
}

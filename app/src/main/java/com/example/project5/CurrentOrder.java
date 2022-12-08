package com.example.project5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 A fragment that manages the current order being placed by user.
 Displays the order number and pizzas that were placed for the order, as well as the ability to
 remove pizzas from the order and add to the order.
 @author Siddh Parmar, Yash Patel
 */
public class CurrentOrder extends Fragment implements  View.OnClickListener{
    ArrayList<CurrentOrderModel> currentOrderModels = new ArrayList<>();
    RecyclerView currentOrderRecyclerView;
    CurrentOrderRecyclerViewAdapter currentOrderAdapter;
    TextView currentOrderOrderNumber;
    EditText currentOrderSubTotal;
    EditText currentOrderSalesTax;
    EditText currentOrderOrderTotal;
    Button currentOrderClearOrderButton;
    Button currentOrderPlaceOrderButton;
    private final DecimalFormat format = new DecimalFormat("#.##");

    /**
     * Creates the view for the Current Order fragment, in which all of the aspects of the
     * current order are displayed
     * @param inflater, instantiates the layout with the Current Order layout
     * @param container, the view that contains other views that are part of the Current
     *                   Order layout
     * @param savedInstanceState, a Bundle that allows the Current Order activity to restore
     *                            itself from previous data
     * @return View, the user interface of the Current Order layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);
        currentOrderRecyclerView = view.findViewById(R.id.currentOrderRecyclerView);
        currentOrderOrderNumber = view.findViewById(R.id.currentOrderOrderNumber);
        currentOrderSubTotal = view.findViewById(R.id.currentOrderSubTotal);
        currentOrderSalesTax = view.findViewById(R.id.currentOrderSalesTax);
        currentOrderOrderTotal = view.findViewById(R.id.currentOrderOrderTotal);
        currentOrderClearOrderButton = view.findViewById(R.id.currentOrderClearOrderButton);
        currentOrderPlaceOrderButton = view.findViewById(R.id.currentOrderPlaceOrderButton);
        currentOrderClearOrderButton.setOnClickListener(this);
        currentOrderPlaceOrderButton.setOnClickListener(this);
        currentOrderModels.clear();
        setUpCurrentOrderModels();
        currentOrderAdapter = new CurrentOrderRecyclerViewAdapter(this.getActivity(),currentOrderModels);
        currentOrderRecyclerView.setAdapter(currentOrderAdapter);
        currentOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        updateCurrentPrice();
        setOrderNumber();
        return view;
    }

    /**
     * Utilized when the Current Order is back in view and can be interacted with.
     * Acts to also update the price and sets the order number such that the current information
     * is displayed.
     */
    @Override
    public void onResume(){
        updateCurrentPrice();
        setOrderNumber();
        super.onResume();

    }

    /**
     * Sets up the information to be displayed for each pizza that is presented in the current
     * order, including its style, flavor, toppings, size, and price
     */
    private void setUpCurrentOrderModels(){
            for (int i = 0; i < MainActivity.order.getPizzas().size(); i++) {
                String flavor = MainActivity.order.getPizzas().get(i).getFlavor();
                String style = "";
                String styleAndCrust;
                String toppings = "";
                String size;
                String price;

                style = MainActivity.order.getPizzas().get(i).getStyle();
                styleAndCrust = "(" + style +
                        " - " + MainActivity.order.getPizzas().get(i).getCrust() + ")";
                for (int j = 0; j < MainActivity.order.getPizzas().get(i).getToppings().size(); j++) {
                    toppings = toppings.concat(MainActivity.order.getPizzas().get(i).getToppings().get(j) + ", ");
                }
                size = MainActivity.order.getPizzas().get(i).getSize().name();
                price = "$" + format.format(MainActivity.order.getPizzas().get(i).price());
                currentOrderModels.add(new CurrentOrderModel(flavor, styleAndCrust, toppings, size, price));

            }
    }

    /**
     * Sets the unique order number for the current order
     */
    public void setOrderNumber(){
        currentOrderOrderNumber.setText(Integer.toString(MainActivity.order.getNumber()));
    }

    /**
     * Updates the current price of the order (subtotal, sales tax, and order total) based off of
     * any pizzas added to and/or removed from the current order
     */
    public void updateCurrentPrice(){
        currentOrderSubTotal.setText("$"+format.format(MainActivity.order.getSubTotal()));
        currentOrderSalesTax.setText("$"+format.format(MainActivity.order.getSalesTax()));
        currentOrderOrderTotal.setText("$"+format.format(MainActivity.order.getOrderTotal()));
    }

    /**
     * Acts to clear all of the pizzas from the current order, such that the current order is empty
     */
    public void clearOrder(){
        for(int i = 0; i < MainActivity.order.getPizzas().size(); i++){
            currentOrderAdapter.currentOrderModels.remove(0);
            currentOrderAdapter.notifyItemRemoved(0);
        }
        MainActivity.order.getPizzas().removeAll(MainActivity.order.getPizzas());
        MainActivity.currentOrder.updateCurrentPrice();
    }

    /**
     * Acts to place the current order such that it adds it to the store orders, as well as
     * incrementing the order number and updating the current price (such that it is $0.00)
     */
    public void placeOrder(){
        if(MainActivity.order.getPizzas().size() > 0) {
            MainActivity.allOrders.getOrders().add(MainActivity.order);
            for(int i = 0; i < MainActivity.order.getPizzas().size(); i++){
                currentOrderAdapter.currentOrderModels.remove(0);
                currentOrderAdapter.notifyItemRemoved(0);
            }

            MainActivity.orderNumbers.add(Integer.toString(MainActivity.currentOrderNumber));
            MainActivity.currentOrderNumber++;
            MainActivity.order = new Order();
            MainActivity.order.setOrderNumber(MainActivity.currentOrderNumber);
            updateCurrentPrice();
            setOrderNumber();
        }
    }


    /**
     * Acts to determine what will occur depending on which button is clicked. Clears order if
     * "CLEAR ORDER" button is clicked, or places order if "PLACE ORDER" button is clicked
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.currentOrderClearOrderButton:
                clearOrder();
                break;
            case R.id.currentOrderPlaceOrderButton:
                placeOrder();
                break;
        }
    }
}
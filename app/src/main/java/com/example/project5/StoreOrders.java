package com.example.project5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *Fragment that manages all of the store orders. Allows user to view and clear orders.
 * @author Siddh Parmar, Yash Patel
 */
public class StoreOrders extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner storeOrderSpinner;
    RecyclerView storeOrderRecyclerView;
    EditText storeOrderOrderTotal;
    Button cancelOrderButton;
    ArrayAdapter<String> storeOrderAdapter;
    StoreOrderRecyclerViewAdapter storeOrderRecyclerAdapter;
    ArrayList<CurrentOrderModel> storeOrderModels = new ArrayList<>();
    private final DecimalFormat format = new DecimalFormat("#.##");

    /**
     * Creates the view for ChicagoStyle.
     * @param inflater Inflater that will inflate the layout with the ChicagoStyle layout.
     * @param container Container that holds the layout.
     * @param savedInstanceState The last saved state of the instance.
     * @return View containing the ChicagoStyle layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_store_orders, container, false);
        storeOrderSpinner = view.findViewById(R.id.storeOrderSpinner);
        storeOrderAdapter = new ArrayAdapter<String>(this.getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, MainActivity.orderNumbers);
        storeOrderRecyclerView = view.findViewById(R.id.storeOrderRecyclerView);
        storeOrderOrderTotal = view.findViewById(R.id.storeOrderOrderTotal);
        cancelOrderButton = view.findViewById(R.id.cancelOrderButton);
        if(MainActivity.orderNumbers.size() > 0) {
            storeOrderModels.clear();
            setUpStoreOrderModels(new Integer(MainActivity.orderNumbers.get(0)));
        }
       storeOrderRecyclerAdapter = new StoreOrderRecyclerViewAdapter(this.getActivity(),storeOrderModels);
        storeOrderRecyclerView.setAdapter(storeOrderRecyclerAdapter);
        storeOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        storeOrderSpinner.setOnItemSelectedListener(this);
        storeOrderSpinner.setAdapter(storeOrderAdapter);
        cancelOrderButton.setOnClickListener(this);
        return view;
    }

    /**
     * Performs actions based on when a view is clicked.
     * @param view View that was clicked.
     */
    @Override
    public void onClick(View view) {
        if(MainActivity.orderNumbers.size() > 0) {
            cancelOrder();
        }
    }

    /**
     * Performs actions when the fragment is resumed.
     */
    @Override
    public void onResume(){
       if(MainActivity.orderNumbers.size() > 0) {
            setOrderTotalText();
        }
        super.onResume();

    }

    /**
     * Listener that performs actions based on the item selected.
     * @param adapterView Holds the adapter on which the item was selected.
     * @param view View on which the item was selected.
     * @param i Position of the selected item.
     * @param l The row id of the item that was clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        storeOrderModels.clear();
        setUpStoreOrderModels(Integer.valueOf(storeOrderSpinner.getSelectedItem().toString()));
        storeOrderRecyclerAdapter.notifyDataSetChanged();
        setOrderTotalText();
    }

    /**
     * Sets text for the order total based on selected order.
     */
    public void setOrderTotalText(){
        storeOrderOrderTotal.setText("$"+format.format(getSpecificOrder(Integer.valueOf(storeOrderSpinner.getSelectedItem().toString())).getOrderTotal()));
    }

    /**
     * Performs actions when no item is selected.
     * @param adapterView The adapter of the view on which nothing was selected.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        storeOrderOrderTotal.setText("");
    }

    /**
     * Returns the specific order that corresponds to the order number
     * @param orderNumber, the order number associated with an order
     * @return Order, the order associated with the order number
     */
    public Order getSpecificOrder(int orderNumber){
        for(int i =0; i <MainActivity.allOrders.getOrders().size(); i++){
            if(MainActivity.allOrders.getOrders().get(i).getNumber() == orderNumber){
                return MainActivity.allOrders.getOrders().get(i);
            }
        }
        return null;
    }

    /**
     * Removes the specific order that corresponds to the order number
     * @param orderNumber, the order number associated with an order
     */
    public void removeSpecificOrder(int orderNumber){
        for(int i =0; i < MainActivity.allOrders.getOrders().size(); i++){
            if(MainActivity.allOrders.getOrders().get(i).getNumber() == orderNumber){
                MainActivity.allOrders.getOrders().remove(MainActivity.allOrders.getOrders().get(i));
            }
        }
    }

    /**
     * Cancels the selected order.
     */
    public void cancelOrder(){
        DecimalFormat format = new DecimalFormat("#.##");
        int orderNumber = Integer.parseInt(storeOrderSpinner.getSelectedItem().toString());
        storeOrderAdapter.remove(storeOrderSpinner.getSelectedItem().toString());
        storeOrderSpinner.setAdapter(storeOrderAdapter);
        storeOrderModels.clear();
        storeOrderRecyclerAdapter.notifyDataSetChanged();
        if(MainActivity.orderNumbers.size() > 0) {
            setUpStoreOrderModels(Integer.valueOf(storeOrderSpinner.getSelectedItem().toString()));
            storeOrderRecyclerAdapter.notifyDataSetChanged();
            setOrderTotalText();
        }
        else{
            storeOrderOrderTotal.setText("");
        }
        removeSpecificOrder(orderNumber);
    }

    /**
     * Sets up the store order model based on the selected order number.
     * @param orderNumber Order number that is selected by user.
     */
    private void setUpStoreOrderModels(int orderNumber){
        Order selectedOrder = getSpecificOrder(orderNumber);
        for (int i = 0; i < selectedOrder.getPizzas().size(); i++) {
            String flavor = selectedOrder.getPizzas().get(i).getFlavor();
            String style = "";
            String styleAndCrust;
            String toppings = "";
            String size;
            String price;
            style = selectedOrder.getPizzas().get(i).getStyle();
            styleAndCrust = "(" + style +
                    " - " + selectedOrder.getPizzas().get(i).getCrust() + ")";
            for (int j = 0; j < selectedOrder.getPizzas().get(i).getToppings().size(); j++) {
                toppings = toppings.concat(selectedOrder.getPizzas().get(i).getToppings().get(j) + ", ");
            }
            size = selectedOrder.getPizzas().get(i).getSize().name();
            price = "$" + format.format(selectedOrder.getPizzas().get(i).price());
            storeOrderModels.add(new CurrentOrderModel(flavor, styleAndCrust, toppings, size, price));

        }
    }
}
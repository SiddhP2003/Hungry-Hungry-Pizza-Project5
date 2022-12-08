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
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreOrders#newInstance} factory method to
 * create an instance of this fragment.
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
           // storeOrderOrderTotal.setText("$"+format.format(getSpecificOrder(Integer.valueOf(MainActivity.orderNumbers.get(0))).getOrderTotal()));
        }
       storeOrderRecyclerAdapter = new StoreOrderRecyclerViewAdapter(this.getActivity(),storeOrderModels);
        storeOrderRecyclerView.setAdapter(storeOrderRecyclerAdapter);
        storeOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        storeOrderSpinner.setOnItemSelectedListener(this);
        storeOrderSpinner.setAdapter(storeOrderAdapter);
        cancelOrderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(MainActivity.orderNumbers.size() > 0) {
            cancelOrder();
        }
    }

    @Override
    public void onResume(){
       if(MainActivity.orderNumbers.size() > 0) {
            setOrderTotalText();
        }
        super.onResume();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            storeOrderModels.clear();
     //   storeOrderRecyclerAdapter.notifyAll();
//        for(int i = 0; i < MainActivity.order.getPizzas().size(); i++){
//            currentOrderAdapter.currentOrderModels.remove(0);
//            currentOrderAdapter.notifyItemRemoved(0);
//        }
        setUpStoreOrderModels(Integer.valueOf(storeOrderSpinner.getSelectedItem().toString()));
        storeOrderRecyclerAdapter.notifyDataSetChanged();
        setOrderTotalText();
       // storeOrderModels.notifyAll();
    }

    public void setOrderTotalText(){
        storeOrderOrderTotal.setText("$"+format.format(getSpecificOrder(Integer.valueOf(storeOrderSpinner.getSelectedItem().toString())).getOrderTotal()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        storeOrderOrderTotal.setText("");
    }

    public Order getSpecificOrder(int orderNumber){
        for(int i =0; i <MainActivity.allOrders.getOrders().size(); i++){
            if(MainActivity.allOrders.getOrders().get(i).getNumber() == orderNumber){
                return MainActivity.allOrders.getOrders().get(i);
            }
        }
        return null;
    }

    public void removeSpecificOrder(int orderNumber){
        for(int i =0; i < MainActivity.allOrders.getOrders().size(); i++){
            if(MainActivity.allOrders.getOrders().get(i).getNumber() == orderNumber){
                MainActivity.allOrders.getOrders().remove(MainActivity.allOrders.getOrders().get(i));
            }
        }
    }

    public void cancelOrder(){
        DecimalFormat format = new DecimalFormat("#.##");
        int orderNumber = Integer.parseInt(storeOrderSpinner.getSelectedItem().toString());
        //MainActivity.orderNumbers.remove(storeOrderSpinner.getSelectedItem().toString());
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
        //orderNumberComboBox.getItems().remove(orderNumberComboBox.getSelectionModel().getSelectedItem());
        //ordersListView.getItems().clear();
        removeSpecificOrder(orderNumber);

      //  orderTotalTextField.setText("");
    }


    private void setUpStoreOrderModels(int orderNumber){
//        if(currentOrderAdapter.currentOrderModels.size() != MainActivity.order.getPizzas().size() && currentOrderAdapter.currentOrderModels.size() != 0) {

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
        //    }
    }
}
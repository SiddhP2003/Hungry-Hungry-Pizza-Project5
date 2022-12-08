package com.example.project5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentOrder extends Fragment {
        ArrayList<CurrentOrderModel> currentOrderModels = new ArrayList<>();
        RecyclerView currentOrderRecyclerView;
        CurrentOrderRecyclerViewAdapter currentOrderAdapter;
        TextView currentOrderOrderNumber;
        EditText currentOrderSubTotal;
        EditText currentOrderSalesTax;
        EditText currentOrderOrderTotal;
    private final DecimalFormat format = new DecimalFormat("#.##");
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
        setUpCurrentOrderModels();
        currentOrderAdapter = new CurrentOrderRecyclerViewAdapter(this.getActivity(),currentOrderModels);
        currentOrderRecyclerView.setAdapter(currentOrderAdapter);
        currentOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        updateCurrentPrice();
        setOrderNumber();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume(){
        updateCurrentPrice();
        setOrderNumber();
        super.onResume();

    }
    String style = "";

    private void setUpCurrentOrderModels(){
            for(int i = 0; i < MainActivity.order.getPizzas().size(); i++){
                String flavor = MainActivity.order.getPizzas().get(i).getFlavor();
                String style= "";
                String styleAndCrust;
                String toppings = "";
                String size;
                String price;
//                if(MainActivity.order.getPizzas().get(i).getCrust().equals(Crust.PAN)){
//            style = "Chicago Style";
//        }
//        else if(MainActivity.order.getPizzas().get(i).getCrust().equals(Crust.HANDTOSSED)){
//            style = "New York Style";
//        }
                style = MainActivity.order.getPizzas().get(i).getStyle();
        styleAndCrust = "("+style+
                " - " + MainActivity.order.getPizzas().get(i).getCrust() + ")";
        for(int j = 0; j < MainActivity.order.getPizzas().get(i).getToppings().size(); j++){
           toppings = toppings.concat(MainActivity.order.getPizzas().get(i).getToppings().get(j) + ", ");
        }
        size = MainActivity.order.getPizzas().get(i).getSize().name();
        price = "$"+format.format(MainActivity.order.getPizzas().get(i).price());
        currentOrderModels.add(new CurrentOrderModel(flavor,styleAndCrust,toppings,size,price));

            }
    }


    public void setOrderNumber(){
        currentOrderOrderNumber.setText(Integer.toString(MainActivity.order.getNumber()));
    }

    public void updateCurrentPrice(){
        currentOrderSubTotal.setText("$"+format.format(MainActivity.order.getSubTotal()));
        currentOrderSalesTax.setText("$"+format.format(MainActivity.order.getSalesTax()));
        currentOrderOrderTotal.setText("$"+format.format(MainActivity.order.getOrderTotal()));
    }
}
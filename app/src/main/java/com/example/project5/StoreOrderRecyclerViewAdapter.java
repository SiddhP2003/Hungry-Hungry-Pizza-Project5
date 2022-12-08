package com.example.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreOrderRecyclerViewAdapter extends RecyclerView.Adapter<StoreOrderRecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<CurrentOrderModel> currentOrderModels;
    public StoreOrderRecyclerViewAdapter(Context context, ArrayList<CurrentOrderModel> currentOrderModels) {
        this.context = context;
        this.currentOrderModels = currentOrderModels;
    }

    @NonNull
    @Override
    public StoreOrderRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.current_order_recycler_row,parent,false);
        return new StoreOrderRecyclerViewAdapter.MyViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreOrderRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.flavorTextView.setText(currentOrderModels.get(position).getFlavor());
        holder.styleAndCrustTextView.setText(currentOrderModels.get(position).getStyleAndCrust());
        holder.toppingsTextView.setText(currentOrderModels.get(position).getToppings());
        holder.sizeTextView.setText(currentOrderModels.get(position).getSize());
        holder.priceTextView.setText(currentOrderModels.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return currentOrderModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        StoreOrderRecyclerViewAdapter helperAdapter;
        TextView flavorTextView;
        TextView styleAndCrustTextView;
        TextView toppingsTextView;
        TextView sizeTextView;
        TextView priceTextView;
        public MyViewHolder(@NonNull View itemView, StoreOrderRecyclerViewAdapter helperAdapter ) {
            super(itemView);
            flavorTextView = itemView.findViewById(R.id.flavorTextView);
            styleAndCrustTextView = itemView.findViewById(R.id.styleAndCrustTextView);
            toppingsTextView = itemView.findViewById(R.id.toppingsTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            itemView.findViewById(R.id.currentOrderRemoveButton).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.currentOrderRemoveButton).setClickable(false);
                    //setOnClickListener(view -> {
//                helperAdapter.currentOrderModels.remove(getAdapterPosition());
//                MainActivity.order.getPizzas().remove(getAdapterPosition());
//                MainActivity.currentOrder.updateCurrentPrice();
//                helperAdapter.notifyItemRemoved(getAdapterPosition());
//            });
        }

    }
}

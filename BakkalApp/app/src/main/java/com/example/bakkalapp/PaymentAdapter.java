package com.example.bakkalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyHolder> {
    ArrayList<String> productNameList;
    ArrayList<Double> totalPriceList;

    public PaymentAdapter(ArrayList<String> productNameList, ArrayList<Double> totalPriceList) {
        this.productNameList = productNameList;
        this.totalPriceList = totalPriceList;
    }

    @NonNull
    @Override
    public PaymentAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_recycler,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.MyHolder holder, int position) {
        holder.textPayName.setText(productNameList.get(position));
        holder.textPayPrice.setText(String.valueOf(totalPriceList.get(position)));
    }

    @Override
    public int getItemCount() {
        return productNameList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textPayName,textPayPrice;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            textPayName = itemView.findViewById(R.id.textPayName);
            textPayPrice = itemView.findViewById(R.id.textPayPrice);
        }
    }
}

package com.example.bakkalapp.panel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakkalapp.R;
import com.example.bakkalapp.details.DetailsActivity;


import java.util.ArrayList;

public class TedarikAdapter extends RecyclerView.Adapter<TedarikAdapter.MyViewHolder> {
    ArrayList<String > firmAdi;
    ArrayList<String> firmaAdres;
    ArrayList<String> firmaNo;
    Context context;

    public TedarikAdapter(ArrayList<String> firmAdi, ArrayList<String> firmaNo, ArrayList<String> firmaAdres,Context context) {
        this.firmAdi = firmAdi;
        this.firmaNo = firmaNo;
        this.firmaAdres = firmaAdres;
        this.context = context;
    }

    @NonNull
    @Override
    public TedarikAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_tedarik,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TedarikAdapter.MyViewHolder holder, int position) {
        holder.textTedarikciAdi.setText("Firma : "+firmAdi.get(position));
        holder.textTedarikciNo.setText("Numara : "+firmaNo.get(position));
        holder.textTedarikciAdres.setText("Adres : "+firmaAdres.get(position));
    }

    @Override
    public int getItemCount() {
        return firmaAdres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTedarikciAdi,textTedarikciAdres,textTedarikciNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTedarikciAdi = itemView.findViewById(R.id.textTedarikciAdi);
            textTedarikciAdres = itemView.findViewById(R.id.textTedarikciAdres);
            textTedarikciNo = itemView.findViewById(R.id.textTedarikciNo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, TedarikDuzenleActivity.class);
            intent.putExtra("firmaAdi",firmAdi.get(position));
            intent.putExtra("firmaNo",firmaNo.get(position));
            intent.putExtra("firmaAdres",firmaAdres.get(position));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}

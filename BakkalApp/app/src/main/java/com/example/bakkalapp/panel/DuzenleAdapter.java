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

public class DuzenleAdapter extends RecyclerView.Adapter<DuzenleAdapter.MyViewHolder> {
    ArrayList<String> urunList;
    ArrayList<String> stokList;
    ArrayList<String> fiyatList;
    ArrayList<String> katIdList;
    Context context;

    public DuzenleAdapter(ArrayList<String> urunList, ArrayList<String> stokList, ArrayList<String> fiyatList, ArrayList<String> katIdList, Context context) {
        this.urunList = urunList;
        this.stokList = stokList;
        this.fiyatList = fiyatList;
        this.katIdList = katIdList;
        this.context = context;
    }

    @NonNull
    @Override
    public DuzenleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.urun_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuzenleAdapter.MyViewHolder holder, int position) {
        holder.textUrunAdi.setText("Ürün adı : "+urunList.get(position));
        holder.textUrunStok.setText("Stok : "+stokList.get(position));
    }

    @Override
    public int getItemCount() {
        return urunList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textUrunAdi,textUrunStok;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textUrunAdi = itemView.findViewById(R.id.textUrunAdi);
            textUrunStok = itemView.findViewById(R.id.textUrunStok);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, UrunDuzenleDetayActivity.class);
            intent.putExtra("ad",urunList.get(position));
            intent.putExtra("stok",stokList.get(position));
            intent.putExtra("fiyat",fiyatList.get(position));
            intent.putExtra("katId",katIdList.get(position));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}

package com.example.bakkalapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakkalapp.R;
import com.example.bakkalapp.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class YiyecekAdapter extends RecyclerView.Adapter<YiyecekAdapter.MyViewHolder> {

    ArrayList<String> imageList;
    ArrayList<String> prodcutList;
    ArrayList<String> idList;
    ArrayList<String> katIdList;
    Context context;

    public YiyecekAdapter(ArrayList<String> imageList, ArrayList<String> prodcutList,ArrayList<String> idList,ArrayList<String> katIdList,  Context context) {
        this.imageList = imageList;
        this.prodcutList = prodcutList;
        this.idList = idList;
        this.katIdList = katIdList;
        this.context = context;

    }

    @NonNull
    @Override
    public YiyecekAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recycler,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull YiyecekAdapter.MyViewHolder holder, int position) {
        holder.textProductName.setText(prodcutList.get(position));
        Picasso.with(holder.imageProduct.getContext()).load(imageList.get(position)).into(holder.imageProduct);


    }

    @Override
    public int getItemCount() {
        return prodcutList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView imageProduct;
        TextView textProductName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            textProductName = itemView.findViewById(R.id.textProductName);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("urunId",idList.get(position));
            intent.putExtra("urunKatId",katIdList.get(position));
            context.startActivity(intent);
        }
    }
}

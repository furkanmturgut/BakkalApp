package com.example.bakkalapp.panel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bakkalapp.R;

public class UrunDuzenleActivity extends AppCompatActivity {
    CardView duzenleBakliyat,duzenleSigara,duzenleIcecek,duzenleYiyecek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_duzenle);

        duzenleBakliyat = findViewById(R.id.duzenleBakliyat);
        duzenleIcecek = findViewById(R.id.duzenleIcecek);
        duzenleSigara = findViewById(R.id.duzenleSigara);
        duzenleYiyecek = findViewById(R.id.duzenleYiyecek);


        duzenleBakliyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DuzenleActivity.class);
                intent.putExtra("kategori","4");
                startActivity(intent);
            }
        });

        duzenleYiyecek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DuzenleActivity.class);
                intent.putExtra("kategori","3");
                startActivity(intent);
            }
        });

        duzenleSigara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DuzenleActivity.class);
                intent.putExtra("kategori","2");
                startActivity(intent);
            }
        });

        duzenleIcecek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DuzenleActivity.class);
                intent.putExtra("kategori","1");
                startActivity(intent);
            }
        });
    }
}
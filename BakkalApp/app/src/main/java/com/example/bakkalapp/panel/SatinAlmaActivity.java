package com.example.bakkalapp.panel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bakkalapp.R;

public class SatinAlmaActivity extends AppCompatActivity {
    CardView cardBakliyat,cardIcecek,cardYiyecek,cardSigara;
    Button buttonDuzenle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satin_alma);

        cardBakliyat = findViewById(R.id.cardBakliyat);
        cardIcecek = findViewById(R.id.cardIcecek);
        cardYiyecek = findViewById(R.id.cardYiyecek);
        cardSigara = findViewById(R.id.cardSigara);
        buttonDuzenle = findViewById(R.id.buttonDuzenle);

        buttonDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SatinAlmaActivity.this,UrunDuzenleActivity.class);
                startActivity(intent);
            }
        });


        cardBakliyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SatinAlmaActivity.this,UrunEkleActivity.class);
                intent.putExtra("id","4");
                intent.putExtra("kategori","Temel");
                startActivity(intent);
            }
        });

        cardYiyecek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SatinAlmaActivity.this,UrunEkleActivity.class);
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });

        cardSigara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SatinAlmaActivity.this,UrunEkleActivity.class);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });

        cardIcecek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SatinAlmaActivity.this,UrunEkleActivity.class);
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });
    }
}
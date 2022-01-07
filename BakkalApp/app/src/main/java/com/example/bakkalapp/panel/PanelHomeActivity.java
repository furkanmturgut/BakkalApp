package com.example.bakkalapp.panel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bakkalapp.R;

public class PanelHomeActivity extends AppCompatActivity {
    CardView cardViewTedarik,cardViewStok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_home);

        cardViewTedarik = findViewById(R.id.cardViewTedarik);
        cardViewStok = findViewById(R.id.cardViewDepo);

        cardViewTedarik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TedarikActivity.class);
                startActivity(intent);
            }
        });

        cardViewStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SatinAlmaActivity.class);
                startActivity(intent);
            }
        });
    }
}
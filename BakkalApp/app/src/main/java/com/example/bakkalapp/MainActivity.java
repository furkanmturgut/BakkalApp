package com.example.bakkalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bakkalapp.panel.PanelActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin,buttonPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonPanel = findViewById(R.id.buttonPanel);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PanelActivity.class);
                startActivity(intent);
            }
        });





    }
}
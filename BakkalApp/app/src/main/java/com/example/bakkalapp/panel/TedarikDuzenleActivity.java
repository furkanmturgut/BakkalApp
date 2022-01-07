package com.example.bakkalapp.panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bakkalapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class TedarikDuzenleActivity extends AppCompatActivity {
    EditText editTextFirmaDuzen,editTextNoDuzen,editTextAdresDuzen;
    String adi,no,adres;
    Button buttonTedarikciDuzen,buttonTedarikciSil;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tedarik_duzenle);

        editTextAdresDuzen = findViewById(R.id.editTextAdresDuzen);
        editTextFirmaDuzen = findViewById(R.id.editTextFirmaDuzen);
        editTextNoDuzen = findViewById(R.id.editTextNoDuzen);
        buttonTedarikciDuzen = findViewById(R.id.buttonTedarikciDuzen);
        buttonTedarikciSil = findViewById(R.id.buttonTedarikciSil);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        adi = bundle.getString("firmaAdi");
        no = bundle.getString("firmaNo");
        adres = bundle.getString("firmaAdres");


        editTextFirmaDuzen.setText(adi);
        editTextNoDuzen.setText(no);
        editTextAdresDuzen.setText(adres);

        buttonTedarikciDuzen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duzenle();
            }
        });

        buttonTedarikciSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil();
            }
        });

    }

    private void sil() {
        firebaseFirestore.collection("Tedarikciler").document(adi).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Silindi!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TedarikDuzenleActivity.this,PanelHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void duzenle() {
        String yeniAd = editTextFirmaDuzen.getText().toString();
        String yeniNo = editTextNoDuzen.getText().toString();
        String yeniAdres = editTextAdresDuzen.getText().toString();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("firmaAdi",yeniAd);
        hashMap.put("firmaNo",yeniNo);
        hashMap.put("firmaAdres",yeniAdres);


        firebaseFirestore.collection("Tedarikciler").document(adi).update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Düzenlendi!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TedarikDuzenleActivity.this,PanelHomeActivity.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
package com.example.bakkalapp.panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bakkalapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class TedarikciEkleActivity extends AppCompatActivity {
    EditText editTextFirmaEkle,editTextNoEkle,editTextAdresEkle;
    Button buttonTedarikciKaydet;
    FirebaseFirestore firebaseFirestore;

    String durum;
    Integer durumInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tedarikci_ekle);

        editTextFirmaEkle = findViewById(R.id.editTextFirmaEkle);
        editTextAdresEkle = findViewById(R.id.editTextAdresEkle);
        editTextNoEkle = findViewById(R.id.editTextNoEkle);
        buttonTedarikciKaydet = findViewById(R.id.buttonTedarikciKaydet);
        firebaseFirestore = FirebaseFirestore.getInstance();



        buttonTedarikciKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firmaEkle(view);
            }
        });
    }


    private void firmaEkle(View view) {
        String adi = editTextFirmaEkle.getText().toString();
        String no = editTextNoEkle.getText().toString();
        String adres = editTextAdresEkle.getText().toString();

        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("firmaAdi",adi);
        hashMap.put("firmaAdres",adres);
        hashMap.put("firmaNo",no);

        firebaseFirestore.collection("Tedarikciler").document(adi).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Snackbar snackbar = Snackbar.make(view,adi+" adlı firma başarıyla eklendi.", BaseTransientBottomBar.LENGTH_SHORT);
                snackbar.show();

                Intent intent = new Intent(getApplication(),PanelHomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
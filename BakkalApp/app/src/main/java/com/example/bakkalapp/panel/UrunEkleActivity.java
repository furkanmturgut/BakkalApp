package com.example.bakkalapp.panel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class UrunEkleActivity extends AppCompatActivity {
    Integer idTemel = 6;
    Integer idIcecek = 8;
    Integer idSigara = 6;
    Integer idYiyecek = 8;
    String id;
    String kategori,urunAdi;
    Integer myId;
    EditText editTextUrunAdi,editTextUrunTedarikci,editTextUrunAlisFiyat,editTextUrunSatisFiyat,editTextUrunMiktar;
    Button buttonUrunEkle;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        initialize();


        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        kategori = bundle.getString("kategori");

        myId = Integer.parseInt(id);


        buttonUrunEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(view);
            }
        });


    }

    public  void addData(View view){
        urunAdi = editTextUrunAdi.getText().toString();
        String urunTedarikci = editTextUrunTedarikci.getText().toString();
        String urunAlisFiyat = editTextUrunAlisFiyat.getText().toString();
        String urunSatisFiyat = editTextUrunSatisFiyat.getText().toString();
        String urunMiktar = editTextUrunMiktar.getText().toString();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("ad",urunAdi);
        hashMap.put("tedarikci",urunTedarikci);
        hashMap.put("alisFiyat",urunAlisFiyat);
        hashMap.put("fiyat",urunSatisFiyat);
        hashMap.put("stok",urunMiktar);
        hashMap.put("kat_id",id);
        hashMap.put("takvim", FieldValue.serverTimestamp());
        hashMap.put("id",String.valueOf(idTemel));


        if (myId == 4){

          firebaseFirestore.collection("Temel").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
              @Override
              public void onSuccess(DocumentReference documentReference) {
                  Snackbar snackbar = Snackbar.make(view,urunAdi+" adlı ürün eklendi.",BaseTransientBottomBar.LENGTH_LONG);
                  snackbar.show();
                  idTemel += 1;
              }
          });
        }else if(myId == 3){
            firebaseFirestore.collection("Tatli").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Snackbar snackbar = Snackbar.make(view,urunAdi+" adlı ürün eklendi.",BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    idYiyecek += 1;
                }
            });
        }else if(myId == 2){
            firebaseFirestore.collection("Sigara").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Snackbar snackbar = Snackbar.make(view,urunAdi+" adlı ürün eklendi.",BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    idSigara += 1;
                }
            });
        }else if(myId == 1){
            firebaseFirestore.collection("Icecek").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Snackbar snackbar = Snackbar.make(view,urunAdi+" adlı ürün eklendi.",BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    idIcecek += 1;
                }
            });
        }
    }



    private void initialize() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        editTextUrunAdi = findViewById(R.id.editTextUrunAdi);
        editTextUrunAlisFiyat = findViewById(R.id.editTextUrunAlisFiyat);
        editTextUrunSatisFiyat = findViewById(R.id.editTextUrunSatisFiyat);
        editTextUrunTedarikci = findViewById(R.id.editTextUrunTedarikci);
        editTextUrunMiktar = findViewById(R.id.editTextUrunMiktar);
        buttonUrunEkle = findViewById(R.id.buttonUrunEkle);
    }


}
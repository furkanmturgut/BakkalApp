package com.example.bakkalapp.panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bakkalapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class UrunDuzenleDetayActivity extends AppCompatActivity {
    String stok,ad,fiyat,katId;
    Button buttonDuzenKaydet;
    TextView textDuzenAd;
    EditText editDuzenStok,editDuzenFiyat;
    FirebaseFirestore firebaseFirestore;
    Integer myId;
    Double myFiyat;
    String lowerAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_duzenle_detay);

        Bundle bundle = getIntent().getExtras();
        stok = bundle.getString("stok");
        ad = bundle.getString("ad");
        fiyat = bundle.getString("fiyat");
        katId = bundle.getString("katId");

        buttonDuzenKaydet = findViewById(R.id.buttonDuzenKaydet);
        textDuzenAd = findViewById(R.id.textDuzenAd);
        editDuzenStok = findViewById(R.id.editDuzenStok);
        editDuzenFiyat = findViewById(R.id.editDuzenFiyat);
        firebaseFirestore = FirebaseFirestore.getInstance();

        textDuzenAd.setText(ad);
        editDuzenStok.setText(stok);
        editDuzenFiyat.setText(fiyat);


        myId = Integer.parseInt(katId);
        lowerAd = ad.toLowerCase();


        buttonDuzenKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(view);
            }
        });


    }

    private void getData(View view) {
        String fiyat = editDuzenFiyat.getText().toString();
        String stok = editDuzenStok.getText().toString();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("fiyat",fiyat);
        hashMap.put("stok",stok);

        if (myId == 3){
            firebaseFirestore.collection("Tatli").document(lowerAd).update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Snackbar snackbar = Snackbar.make(view,ad+" adlı ürün güncellendi.", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    Intent intent = new Intent(getApplicationContext(),PanelHomeActivity.class);
                    startActivity(intent);
                }
            });
        }else if(myId == 4){
            firebaseFirestore.collection("Temel").document(lowerAd).update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Snackbar snackbar = Snackbar.make(view,ad+" adlı ürün güncellendi.", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    Intent intent = new Intent(getApplicationContext(),PanelHomeActivity.class);
                    startActivity(intent);
                }
            });
        }else if (myId == 2){
            firebaseFirestore.collection("Sigara").document(lowerAd).update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Snackbar snackbar = Snackbar.make(view,ad+" adlı ürün güncellendi.", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    Intent intent = new Intent(getApplicationContext(),PanelHomeActivity.class);
                    startActivity(intent);
                }
            });
        }else if(myId == 1){
            firebaseFirestore.collection("Icecek").document(lowerAd).update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Snackbar snackbar = Snackbar.make(view,ad+" adlı ürün güncellendi.", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                    Intent intent = new Intent(getApplicationContext(),PanelHomeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
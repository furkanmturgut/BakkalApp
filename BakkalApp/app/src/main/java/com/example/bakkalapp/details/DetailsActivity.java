package com.example.bakkalapp.details;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakkalapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageDetail;
    TextView textDetailName,textDetailStok,textDetailPrice;
    Button buttonDetail;
    FirebaseFirestore firebaseFirestore;
    String productId,productCatId;
    Integer id;
    EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        productCatId = bundle.getString("urunKatId");
        productId = bundle.getString("urunId");
        //Toast.makeText(DetailsActivity.this, productCatId, Toast.LENGTH_SHORT).show();

       imageDetail = findViewById(R.id.imageDetail);
       textDetailName = findViewById(R.id.textDetailName);
       textDetailPrice = findViewById(R.id.textDetailPrice);
       textDetailStok = findViewById(R.id.textDetailStok);
       buttonDetail = findViewById(R.id.buttonDetail);
       editTextNumber = findViewById(R.id.editTextNumber);
       firebaseFirestore = FirebaseFirestore.getInstance();

        id = Integer.parseInt(productCatId);


        if (id == 3){
           getYiyecekDetail();
        }else if(id == 1) {
            getDrinkDetail();
        }else if(id == 2){
            getSmokeDetail();
        }else if(id == 4){
            getBakliyatDetail();
        }

        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == 4){
                    getSharedPreferencesMethod(view);
                }else if(id == 3) {
                    getSharedPreferencesMethod(view);
                }else if(id == 2){
                    getSharedPreferencesMethod(view);
                }else if(id == 1){
                    getSharedPreferencesMethod(view);
                }
            }
        });




    }

    public void getSharedPreferencesMethod(View view){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String name = sharedPref.getString("name","Kayıt Yok");
        String fiyat = sharedPref.getString("price","Kayıt Yok");
        String stok = sharedPref.getString("stock","Kayıt Yok");

        Integer myStok = Integer.parseInt(stok);

        String adet = editTextNumber.getText().toString();
        Integer myAdet = Integer.parseInt(adet);

        Double myPrice = Double.parseDouble(fiyat);
        Double totalPrice = Double.valueOf(myPrice * myAdet);


        Toast.makeText(getApplication(), "Sonuç: "+totalPrice, Toast.LENGTH_SHORT).show();


        if (myAdet > myStok){
            Toast.makeText(getApplication(), "Stok sayından fazla ürün satamazsın!", Toast.LENGTH_LONG).show();
        }else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("urunAdi", name);
            hashMap.put("urunFiyat", fiyat);
            hashMap.put("urunAdet", adet);
            hashMap.put("urunToplamFiyat",totalPrice);
            hashMap.put("tarihTimestamp", FieldValue.serverTimestamp());

            firebaseFirestore.collection("Sepetim").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Snackbar snackbar = Snackbar.make(view, name+" adlı ürün sepete eklendi", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }







    }

    public void setSharedPreferencedMethod(String name,String price,String stock){

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        SharedPreferences sharedPanel = this.getSharedPreferences("sharedPanel",Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPanel.edit();
        myEditor.putString("stok",stock);

        editor.putString("name",name);
        editor.putString("price",price);
        editor.putString("stock",stock);

        editor.commit();
    }



    private void getBakliyatDetail() {
        firebaseFirestore.collection("Temel")
                .whereEqualTo("id",productId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String fiyat = (String) map.get("fiyat");
                        String stok = (String) map.get("stok");

                        textDetailName.setText("Ürün adı : "+name);
                        textDetailStok.setText("Stok sayısı : "+stok);
                        textDetailPrice.setText("Ürün fiyatı : "+fiyat+" TL");



                        setSharedPreferencedMethod(name,fiyat,stok);
                    }

                }

            }
        });

    }

    private void getSmokeDetail() {
        firebaseFirestore.collection("Sigara")
                .whereEqualTo("id",productId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String fiyat = (String) map.get("fiyat");
                        String stok = (String) map.get("stok");

                        textDetailName.setText("Ürün adı : "+name);
                        textDetailStok.setText("Stok sayısı : "+stok);
                        textDetailPrice.setText("Ürün fiyatı : "+fiyat+" TL");

                        setSharedPreferencedMethod(name,fiyat,stok);



                    }
                }
            }
        });
    }


    public void getDrinkDetail(){
        firebaseFirestore.collection("Icecek")
                .whereEqualTo("id",productId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String fiyat = (String) map.get("fiyat");
                        String stok = (String) map.get("stok");

                        textDetailName.setText("Ürün adı : "+name);
                        textDetailStok.setText("Stok sayısı : "+stok);
                        textDetailPrice.setText("Ürün fiyatı : "+fiyat+" TL");

                        setSharedPreferencedMethod(name,fiyat,stok);

                    }
                }
            }
        });
    }


    public void getYiyecekDetail(){
        firebaseFirestore.collection("Tatli")
       .whereEqualTo("id",productId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String fiyat = (String) map.get("fiyat");
                        String stok = (String) map.get("stok");

                        textDetailName.setText("Ürün adı : "+name);
                        textDetailStok.setText("Stok sayısı : "+stok);
                        textDetailPrice.setText("Ürün fiyatı : "+fiyat+" TL");

                        setSharedPreferencedMethod(name,fiyat,stok);

                    }
                }
            }
        });
    }

}
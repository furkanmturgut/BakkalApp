package com.example.bakkalapp.panel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bakkalapp.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class DuzenleActivity extends AppCompatActivity {
    RecyclerView recyclerDuzenle;
    DuzenleAdapter duzenleAdapter;
    ArrayList<String> urunAdiList,stoklist,fiyatList,katIdList;
    FirebaseFirestore firebaseFirestore;
    String kategori;
    Integer myKategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duzenle);

        Bundle bundle = getIntent().getExtras();
        kategori = bundle.getString("kategori");
        myKategori = Integer.parseInt(kategori);



        recyclerDuzenle = findViewById(R.id.recyclerUrun);
        urunAdiList = new ArrayList<>();
        stoklist = new ArrayList<>();
        fiyatList = new ArrayList<>();
        katIdList = new ArrayList<>();
        duzenleAdapter =  new DuzenleAdapter(urunAdiList,stoklist,fiyatList,katIdList,getApplicationContext());
        recyclerDuzenle.setLayoutManager(new LinearLayoutManager(this));
        recyclerDuzenle.setAdapter(duzenleAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();

    }

    private void getData() {
        if (myKategori == 4){
            CollectionReference collectionReference = firebaseFirestore.collection("Temel");
            collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String stock = (String) map.get("stok");
                        String fiyat = (String) map.get("fiyat");
                        String katId = (String) map.get("kat_id");



                        urunAdiList.add(name);
                        stoklist.add(stock);
                        fiyatList.add(fiyat);
                        katIdList.add(katId);


                        duzenleAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else if(myKategori == 3){
            CollectionReference collectionReference = firebaseFirestore.collection("Tatli");
            collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String stock = (String) map.get("stok");
                        String fiyat = (String) map.get("fiyat");
                        String katId = (String) map.get("kat_id");



                        urunAdiList.add(name);
                        stoklist.add(stock);
                        fiyatList.add(fiyat);
                        katIdList.add(katId);


                        duzenleAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else if(myKategori == 2){
            CollectionReference collectionReference = firebaseFirestore.collection("Sigara");
            collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String stock = (String) map.get("stok");
                        String fiyat = (String) map.get("fiyat");
                        String katId = (String) map.get("kat_id");


                        urunAdiList.add(name);
                        stoklist.add(stock);
                        katIdList.add(katId);
                        fiyatList.add(fiyat);


                        duzenleAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else if(myKategori == 1){
            CollectionReference collectionReference = firebaseFirestore.collection("Icecek");
            collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("ad");
                        String stock = (String) map.get("stok");
                        String fiyat = (String) map.get("fiyat");
                        String katId = (String) map.get("kat_id");


                        urunAdiList.add(name);
                        stoklist.add(stock);
                        katIdList.add(katId);
                        fiyatList.add(fiyat);


                        duzenleAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
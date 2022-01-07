package com.example.bakkalapp.panel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bakkalapp.R;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class TedarikActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    TedarikAdapter tedarikAdapter;
    ArrayList<String > firmAdi;
    ArrayList<String> firmaAdres;
    ArrayList<String> firmaNo;
    RecyclerView recyclerView;
    Button buttonTedarikciEkle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tedarik);

        recyclerView = findViewById(R.id.recycler_tedarikci);
        buttonTedarikciEkle = findViewById(R.id.buttonTedarikciEkle);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firmaAdres = new ArrayList<>();
        firmAdi = new ArrayList<>();
        firmaNo = new ArrayList<>();
        tedarikAdapter = new TedarikAdapter(firmAdi,firmaNo,firmaAdres,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tedarikAdapter);

        buttonTedarikciEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TedarikActivity.this,TedarikciEkleActivity.class);
                startActivity(intent);
            }
        });




        getData();

    }



    private void getData() {
       firebaseFirestore.collection("Tedarikciler")
       .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    for (DocumentSnapshot documentSnapshot : value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String firma = (String) map.get("firmaAdi");
                        String no = (String) map.get("firmaNo");
                        String adres = (String) map.get("firmaAdres");


                        firmAdi.add(firma);
                        firmaNo.add(no);
                        firmaAdres.add(adres);


                        tedarikAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
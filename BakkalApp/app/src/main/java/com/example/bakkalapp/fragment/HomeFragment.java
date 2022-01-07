package com.example.bakkalapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bakkalapp.R;
import com.example.bakkalapp.adapter.BakliyatAdapter;
import com.example.bakkalapp.adapter.SmokeAdapter;
import com.example.bakkalapp.adapter.SoftDrinkAdapter;
import com.example.bakkalapp.adapter.YiyecekAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class HomeFragment extends Fragment {
    RecyclerView recyclerSoftDrink,recyclerSmoke,recyclerBakliyat,recyclerYiyecek;

    SoftDrinkAdapter softDrinkAdapter;
    SmokeAdapter smokeAdapter;
    BakliyatAdapter bakliyatAdapter;
    YiyecekAdapter yiyecekAdapter;

    ArrayList<String> imageList,softList,katIdSoftList,idSoftList;
    ArrayList<String> smokeList,smokePhoto,smokeKatIdList,smokeIdList;
    ArrayList<String> bakliyatList,imageBakliyat,karIdBakliyat,idBakliyatList;
    ArrayList<String> imageYiyecek,yiyecekList,idListYiyecek,katIdList;

    FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerSoftDrink = view.findViewById(R.id.recyclerSoftDrink);
        recyclerSmoke = view.findViewById(R.id.recyclerSmoke);
        recyclerBakliyat = view.findViewById(R.id.recyclerBakliyat);
        recyclerYiyecek = view.findViewById(R.id.recyclerYiyecek);
        firestore = FirebaseFirestore.getInstance();

        //ArrayList's
        imageList = new ArrayList<>();
        softList = new ArrayList<>();
        smokeList = new ArrayList<>();
        smokePhoto = new ArrayList<>();
        bakliyatList = new ArrayList<>();
        imageBakliyat = new ArrayList<>();
        imageYiyecek = new ArrayList<>();
        yiyecekList = new ArrayList<>();
        idListYiyecek = new ArrayList<>();
        katIdList = new ArrayList<>();
        katIdSoftList = new ArrayList<>();
        idSoftList = new ArrayList<>();
        smokeKatIdList = new ArrayList<>();
        smokeIdList = new ArrayList<>();
        idBakliyatList = new ArrayList<>();
        karIdBakliyat = new ArrayList<>();



        softDrinkAdapter = new SoftDrinkAdapter(imageList,softList,katIdSoftList,idSoftList,getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerSoftDrink.setLayoutManager(layoutManager);
        recyclerSoftDrink.setAdapter(softDrinkAdapter);

        smokeAdapter = new SmokeAdapter(smokePhoto,smokeList,smokeKatIdList,smokeIdList,getContext());

        RecyclerView.LayoutManager layoutManagers = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerSmoke.setLayoutManager(layoutManagers);
        recyclerSmoke.setAdapter(smokeAdapter);

        bakliyatAdapter = new BakliyatAdapter(imageBakliyat,bakliyatList,idBakliyatList,karIdBakliyat,getContext());

        RecyclerView.LayoutManager layoutMngr = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerBakliyat.setLayoutManager(layoutMngr);
        recyclerBakliyat.setAdapter(bakliyatAdapter);

        yiyecekAdapter = new YiyecekAdapter(imageYiyecek,yiyecekList,idListYiyecek,katIdList,getContext());

        RecyclerView.LayoutManager layoutMngrs = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerYiyecek.setLayoutManager(layoutMngrs);
        recyclerYiyecek.setAdapter(yiyecekAdapter);




        getDataDrink();
        getDataSmoke();
        getDataBakliyat();
        getDataYiyecek();
    }

    private void getDataYiyecek() {

        CollectionReference collectionReference = firestore.collection("Tatli");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                    Map<String,Object> map = documentSnapshot.getData();

                    String image = (String) map.get("resim");
                    String name = (String) map.get("ad");
                    String id = (String) map.get("id");
                    String katID = (String) map.get("kat_id");

                    imageYiyecek.add(image);
                    yiyecekList.add(name);
                    idListYiyecek.add(id);
                    katIdList.add(katID);

                    yiyecekAdapter.notifyDataSetChanged();
                }
            }
        });
    }



    private void getDataBakliyat() {
        CollectionReference collectionReference = firestore.collection("Temel");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                    Map<String,Object> map = documentSnapshot.getData();

                    String image = (String) map.get("resim");
                    String name = (String) map.get("ad");
                    String katId = (String) map.get("kat_id");
                    String id = (String) map.get("id");

                    imageBakliyat.add(image);
                    bakliyatList.add(name);
                    karIdBakliyat.add(katId);
                    idBakliyatList.add(id);

                    bakliyatAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void getDataDrink(){
        CollectionReference collectionReference = firestore.collection("Icecek");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                    Map<String,Object> map = documentSnapshot.getData();

                    String image = (String) map.get("resim");
                    String name = (String) map.get("ad");
                    String id = (String) map.get("id");
                    String katID = (String) map.get("kat_id");

                    imageList.add(image);
                    softList.add(name);
                    katIdSoftList.add(katID);
                    idSoftList.add(id);

                    softDrinkAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void getDataSmoke(){
        CollectionReference collectionReference = firestore.collection("Sigara");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot documentSnapshot:value.getDocuments()){
                    Map<String,Object> map = documentSnapshot.getData();

                    String image = (String) map.get("resim");
                    String name = (String) map.get("ad");
                    String katId = (String) map.get("kat_id");
                    String id = (String) map.get("id");

                    smokePhoto.add(image);
                    smokeList.add(name);
                    smokeKatIdList.add(katId);
                    smokeIdList.add(id);

                    smokeAdapter.notifyDataSetChanged();
                }
            }
        });
    }


}


package com.example.bakkalapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bakkalapp.HomeActivity;
import com.example.bakkalapp.PaymentAdapter;
import com.example.bakkalapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class PaymentFragment extends Fragment {
    RecyclerView recycler_sepet;
    Button buttonPayment;
    PaymentAdapter paymentAdapter;
    ArrayList<String> nameList;
    ArrayList<Double> priceList;
    FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler_sepet = view.findViewById(R.id.recycler_sepet);
        buttonPayment = view.findViewById(R.id.buttonPayment);
        firestore = FirebaseFirestore.getInstance();

        nameList = new ArrayList<>();
        priceList = new ArrayList<>();

        paymentAdapter = new PaymentAdapter(nameList,priceList);
        recycler_sepet.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_sepet.setAdapter(paymentAdapter);

        getData();
        buttonPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("Sepetim").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {




                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentsId = documentSnapshot.getId();

                            firestore.collection("Sepetim").document(documentsId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void aVoid) {
                                    Intent intent = new Intent(getContext(),HomeActivity.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    }
                });

            }
        });



    }

    private void getData() {
        CollectionReference collectionReference = firestore.collection("Sepetim");
        collectionReference.orderBy("tarihTimestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    for (DocumentSnapshot documentSnapshot: value.getDocuments()){
                        Map<String,Object> map = documentSnapshot.getData();

                        String name = (String) map.get("urunAdi");
                        Double price = (Double) map.get("urunToplamFiyat");

                        nameList.add(name);
                        priceList.add(price);



                        paymentAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
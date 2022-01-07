package com.example.bakkalapp.panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bakkalapp.HomeActivity;
import com.example.bakkalapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PanelActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText editPanelMail,editPanelPass;
    Button buttonPanelHome;
    Boolean mailControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        firebaseAuth = FirebaseAuth.getInstance();
        editPanelMail = findViewById(R.id.editPanelMail);
        editPanelPass = findViewById(R.id.editPanelPass);
        buttonPanelHome = findViewById(R.id.buttonPanelHome);

        buttonPanelHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPanel(view);
            }
        });
    }

    public void goToPanel(View view) {
        String mail = editPanelMail.getText().toString();
        String pass = editPanelPass.getText().toString();

        mailControl = mail.contains("@");
        mailControl = mail.contains(".com");


        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
            Snackbar snackbar = Snackbar.make(view, "Geçerli alanı doldurunuz", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        } else if (pass.length() < 6) {
            Snackbar snackbar = Snackbar.make(view, "Geçerli şifre giriniz", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        } else if (mailControl != true) {
            Snackbar snackbar = Snackbar.make(view, "Geçerli mail giriniz", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(PanelActivity.this, PanelHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar snackbar = Snackbar.make(view, "İletişime geçiniz", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }
    }
}
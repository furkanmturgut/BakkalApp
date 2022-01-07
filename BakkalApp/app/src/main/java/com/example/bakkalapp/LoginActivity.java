package com.example.bakkalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText editTextMail,editTextPass;
    Button buttonHome;
    FirebaseAuth firebaseAuth;
    Boolean mailControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome(view);
            }
        });
    }

    private void goToHome(View view) {
        String mail = editTextMail.getText().toString();
        String pass = editTextPass.getText().toString();
        mailControl = mail.contains("@");
        mailControl = mail.contains(".com");


        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)){
            Snackbar snackbar = Snackbar.make(view,"Geçerli alanı doldurunuz", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }else if(pass.length() < 6){
            Snackbar snackbar = Snackbar.make(view,"Geçerli şifre giriniz", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }else if(mailControl != true){
            Snackbar snackbar = Snackbar.make(view,"Geçerli mail giriniz", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }else {
            firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar snackbar = Snackbar.make(view,"İletişime geçiniz", BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }
    }

    private void initialize() {
        editTextMail = findViewById(R.id.editTextMail);
        editTextPass = findViewById(R.id.editTextPass);
        buttonHome = findViewById(R.id.buttonHome);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
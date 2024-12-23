package com.example.firebase1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    boolean isplaying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent gi = getIntent();
        isplaying = gi.getBooleanExtra("Music_playing",false);
    }

    public void Acrivity2(View view) {
        Intent si = new Intent(this, Activity2.class);
        startActivity(si);
    }
    public void Acrivity3(View view) {
        Intent si = new Intent(this, Activity3.class);
        startActivity(si);
    }
    public void Acrivity4(View view) {
        Intent si = new Intent(this, Activity4.class);
        startActivity(si);
    }
    public void Acrivity5(View view) {
        Intent si = new Intent(this, Activity5.class);
        si.putExtra("Music_playing",isplaying);
        startActivity(si);
    }
    public void Acrivity6(View view) {
        Intent si = new Intent(this, Activity6.class);
        startActivity(si);
    }
}
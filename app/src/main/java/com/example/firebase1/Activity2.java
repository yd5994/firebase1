package com.example.firebase1;

import static com.example.firebase1.Activity2.FBRef.refAuth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Activity2 extends AppCompatActivity {

    EditText eTEmail,eTPass;
    Button createUser;
    TextView tVMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public static class FBRef{
        public static FirebaseAuth refAuth = FirebaseAuth.getInstance();
    }

    public void createUser(View view) {
        eTEmail = findViewById(R.id.editTextTextEmailAddress);
        eTPass = findViewById(R.id.editTextTextPassword);
        createUser = findViewById(R.id.button);
        tVMsg = findViewById(R.id.textView);

        String email = eTEmail.getText().toString();
        String pass = eTPass.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {

            tVMsg.setText("Please fill all fields");

        } else {

            ProgressDialog pd = new ProgressDialog(this);

            pd.setTitle("Connecting");

            pd.setMessage("Creating user...");

            pd.show();

            refAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {

                            pd.dismiss();

                            if (task.isSuccessful()) {

                                Log.i("MainActivity", "createUserWithEmailAndPassword:success");

                                FirebaseUser user = refAuth.getCurrentUser();

                                tVMsg.setText("User created successfully\nUid: " + user.getUid());
                            } else {

                                Exception exp = task.getException();

                                if (exp instanceof FirebaseAuthInvalidUserException) {

                                    tVMsg.setText("Invalid email address.");

                                } else if (exp instanceof FirebaseAuthWeakPasswordException) {

                                    tVMsg.setText("Password too weak.");

                                } else if (exp instanceof FirebaseAuthUserCollisionException) {

                                    tVMsg.setText("User already exists.");

                                } else if (exp instanceof FirebaseAuthInvalidCredentialsException) {

                                    tVMsg.setText("General authentication failure.");

                                } else if (exp instanceof FirebaseNetworkException) {

                                    tVMsg.setText("Network error. Please check your connection.");

                                } else {

                                    tVMsg.setText("An error occurred. Please try again later.");

                                }

                            }

                        }

                    });

        }
    }
}
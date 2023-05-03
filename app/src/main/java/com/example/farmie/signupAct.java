package com.example.farmie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupAct extends AppCompatActivity {

    TextView toLogin;
    EditText name, email, passwd;
    FirebaseAuth mAuth;
    String uName, uPass, uEmail;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toLogin= findViewById(R.id.toLogin);
        regBtn= findViewById(R.id.suSub);

        name= findViewById(R.id.suName);
        email= findViewById(R.id.suEmail);
        passwd= findViewById(R.id.suPass);

        mAuth= FirebaseAuth.getInstance();

        toLogin.setOnClickListener(v -> {
            Intent i= new Intent(signupAct.this, MainActivity.class);
            startActivity(i);
            finish();
        });

        regBtn.setOnClickListener(view -> {
            uName=name.getText().toString().trim();
            uEmail= email.getText().toString().trim();
            uPass= passwd.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(uEmail, uPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(signupAct.this, "User has been registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(signupAct.this, MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(signupAct.this, "Error: Not registered", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
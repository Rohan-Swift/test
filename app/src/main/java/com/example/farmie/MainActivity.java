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

public class MainActivity extends AppCompatActivity {

    TextView toSign;
    String uName, uPasswd;
    EditText name, passwd;
    Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toSign= findViewById(R.id.toSign);

        name= findViewById(R.id.loginUname);
        passwd= findViewById(R.id.loginPass);
        login= findViewById(R.id.loginSub);

        mAuth=FirebaseAuth.getInstance();





            login.setOnClickListener(v -> {
                uName=name.getText().toString().trim();
                uPasswd= passwd.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(uName, uPasswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, buySell.class));
                            finish();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Login unsuccessful", Toast.LENGTH_LONG).show();
                    }
                });
            });


        toSign.setOnClickListener(v -> {
            Intent i= new Intent(MainActivity.this, signupAct.class);
            startActivity(i);
            finish();
        });
    }
}
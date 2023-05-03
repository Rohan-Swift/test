package com.example.farmie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sellAct extends AppCompatActivity {

    EditText riceAdd1, whtAdd;
    Button b1;
    int riceDB, rice1, riceTot;
    String riceQuan, riceET, whtQuan, total;
    ImageView img1, img2;

    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rice= db.getReference("Prod1").child("Quantity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        img1= findViewById(R.id.img1);
        img2= findViewById(R.id.img2);
        b1= findViewById(R.id.backbtn);

        riceAdd1=findViewById(R.id.riceAdd);
        whtAdd= findViewById(R.id.whtAdd);

        b1.setOnClickListener(v -> {
            Intent i= new Intent(sellAct.this, buySell.class);
            startActivity(i);
            finish();
        });



        img1.setOnClickListener(v -> {
            rice.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    riceDB = dataSnapshot.getValue(Integer.class);
                    //riceDB= Integer.parseInt(riceQuan);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            riceET= riceAdd1.getText().toString();
            rice1= Integer.parseInt(riceET);
            riceTot= rice1+riceDB;
            rice.setValue(riceTot);
            Toast.makeText(this, "Rice quantity added", Toast.LENGTH_SHORT).show();
        });
    }
}
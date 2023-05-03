package com.example.farmie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class buyAct extends AppCompatActivity {

    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rice= db.getReference("Prod1").child("Quantity");
    DatabaseReference riceCart= db.getReference("Prod1").child("cart");

    ImageView img1, img2;
    EditText riceBuy, whtBuy;
    String  buyRice;
    int riceNum, riceNum1, riceQuan, riceDiff;
    FloatingActionButton toCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        img1= findViewById(R.id.buyimg1);

        riceBuy= findViewById(R.id.ricebuy);

        toCart= findViewById(R.id.cartBtn);

        toCart.setOnClickListener(v -> {
            Intent i= new Intent(buyAct.this, cart.class);
            startActivity(i);
            finish();
        });

        rice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                riceQuan = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        img1.setOnClickListener(v -> {

            riceNum1= Integer.parseInt(riceBuy.getText().toString());
            riceCart.setValue(riceBuy.getText().toString());

            rice.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    riceQuan = dataSnapshot.getValue(Integer.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if(riceNum1>riceQuan)
            {
                AlertDialog riceQuanAlert = new AlertDialog.Builder(buyAct.this).create();
                riceQuanAlert.setTitle("Rice");
                riceQuanAlert.setMessage("Quantity of rice available is less than what you want");

                riceQuanAlert.setButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        riceQuanAlert.dismiss();
                    }                });

                riceQuanAlert.show();
            }
            else
            {
                riceDiff= riceQuan-riceNum1;
                rice.setValue(riceDiff);
            }
        });
    }
}
package com.example.farmie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cart extends AppCompatActivity {

    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rice= db.getReference("Prod1").child("cart");
    TextView toBuy, riceDisp, totDisp;
    Button btn;
    String num1;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toBuy=findViewById(R.id.toBuy);
        riceDisp= findViewById(R.id.riceDisp);
        totDisp= findViewById(R.id.total);

        btn= findViewById(R.id.checkout);


        rice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                num1 = dataSnapshot.getValue(String.class);
                riceDisp.setText(num1);
                a= Integer.parseInt(riceDisp.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        btn.setOnClickListener(v -> {
            int sum= a*50;
            totDisp.setText(sum+"");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i= new Intent(cart.this, payment.class);
                    startActivity(i);
                    finish();
                }
            }, 4000);
        });

        toBuy.setOnClickListener(v -> {
            Intent i = new Intent(cart.this, buyAct.class);
            startActivity(i);
            finish();
        });
    }
}

package com.example.farmie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(payment.this, buySell.class);
                startActivity(i);
                finish();
            }
        }, 6000);
    }
}

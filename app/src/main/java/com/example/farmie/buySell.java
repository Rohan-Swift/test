package com.example.farmie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class buySell extends AppCompatActivity {

    Button buy, sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);

        buy= findViewById(R.id.buy);
        sell= findViewById(R.id.sell);

        buy.setOnClickListener(v -> {
            Intent i= new Intent(buySell.this, buyAct.class);
            startActivity(i);
        });

        sell.setOnClickListener(v -> {
            Intent i= new Intent(buySell.this, sellAct.class);
            startActivity(i);
            finish();
        });
    }
}
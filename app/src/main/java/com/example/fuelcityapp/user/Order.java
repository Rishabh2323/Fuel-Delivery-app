package com.example.fuelcityapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fuelcityapp.R;


public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Button btn_petrol,btn_diesel;

        btn_petrol=findViewById(R.id.btn_petrol);
        btn_diesel=findViewById(R.id.btn_diesel);

        btn_petrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_petrolIntent=new Intent(getApplicationContext(), Petrol_Order.class);
                startActivity(btn_petrolIntent);
            }
        });

        btn_diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_dieselIntent=new Intent(getApplicationContext(), Diesel_Order.class);
                startActivity(btn_dieselIntent);
            }
        });


    }
}
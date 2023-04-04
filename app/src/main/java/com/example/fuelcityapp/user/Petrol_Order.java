package com.example.fuelcityapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelcityapp.Database.PetrolOrderAdapter;
import com.example.fuelcityapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Calendar;

public class
Petrol_Order extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_order);

        TextView TV_po,TV_po1,TV_po2,TV_po3,TV_po4;
        EditText ED_po,ED_po2,ED_po3,ED_po4,ED_po5;
        Button petrol_order3,petrol_online;
        PetrolOrderAdapter DBp;
        Calendar calendar=Calendar.getInstance();


        //TEXT VIEWS
        TV_po=findViewById(R.id.TV_po);
        TV_po1=findViewById(R.id.TV_po1);
        TV_po2=findViewById(R.id.TV_po2);
        TV_po3=findViewById(R.id.TV_po3);
        TV_po4=findViewById(R.id.TV_po4);


        //EDITTEXT
        ED_po=findViewById(R.id.ED_po);
        ED_po2=findViewById(R.id.ED_po2);
        ED_po3=findViewById(R.id.ED_po3);
        ED_po4=findViewById(R.id.ED_po4);

        //BUTTON
        petrol_online=findViewById(R.id.petrol_on_order3);
        petrol_order3=findViewById(R.id.petrol_order3);
        DBp = new PetrolOrderAdapter(this);





        petrol_order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Quantity=ED_po2.getText().toString();
                String Date=ED_po.getText().toString();
                String Time=ED_po3.getText().toString();
                String Address=ED_po4.getText().toString();

                if (Quantity.length()<=1){
                if(Quantity.equals("")||Date.equals("") ||Time.equals("")||Address.equals("")){
                    Toast.makeText(Petrol_Order.this, "You miss Something please recheck once :)", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(Petrol_Order.this, "quantity must be 10", Toast.LENGTH_SHORT).show();
                }}
                else{
                    Boolean insert= DBp.insertdata(Quantity,Date,Time,Address);
                    Toast.makeText(Petrol_Order.this, "Order Placed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        petrol_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Quantity=ED_po2.getText().toString();
                String Date=ED_po.getText().toString();
                String Time=ED_po3.getText().toString();
                String Address=ED_po4.getText().toString();
                if (Quantity.length()<=1){
                    if(Quantity.equals("")||Date.equals("") ||Time.equals("")||Address.equals("")){
                        Toast.makeText(Petrol_Order.this, "You miss Something please recheck once :)", Toast.LENGTH_SHORT).show();}
                    else {
                        Toast.makeText(Petrol_Order.this, "quantity must be 10", Toast.LENGTH_SHORT).show();
                    }}
                else{
                    Boolean insert= DBp.insertdata(Quantity,Date,Time,Address);
                    startpayment();
                    Toast.makeText(Petrol_Order.this, "Order Placed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void startpayment () {
        Checkout checkout=new Checkout();
        final Activity activity=this;
        try {
            JSONObject options = new JSONObject();
            options.put("name", R.string.app_name);
            options.put("description", "Payment for Anything");
            options.put("send sms hash", true);
            options.put("allow rotation", false);

            options.put("currency", "INR");
            options.put("amount", "10000");

            JSONObject prefill = new JSONObject();
            prefill.put("email", " ");
            prefill.put("contact", " ");

            options.put("prefill", prefill);

            checkout.open(activity, options);
        }
        catch (Exception e) {
            Toast.makeText(activity,"Error in payment: "+e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(this, "successful Payment", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Unsuccessful Payment", Toast.LENGTH_SHORT).show();

    }

}
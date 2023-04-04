package com.example.fuelcityapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelcityapp.Database.DieselOrderAdapter;
import com.example.fuelcityapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Diesel_Order extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diesel_order);

        TextView TV_do, TV_do1, TV_do2, TV_do3, TV_do4;
        EditText ED_do, ED_do2, ED_do3, ED_do4;
        Button diesel_order3, online_order;
        DieselOrderAdapter DBd;


        //TEXTVIEW
        TV_do = findViewById(R.id.TV_do);
        TV_do1 = findViewById(R.id.TV_do1);
        TV_do2 = findViewById(R.id.TV_do2);
        TV_do3 = findViewById(R.id.TV_do3);
        TV_do4 = findViewById(R.id.TV_do4);

        //EDITTEXT
        ED_do = findViewById(R.id.ED_do);
        ED_do2 = findViewById(R.id.ED_do2);
        ED_do3 = findViewById(R.id.ED_do3);
        ED_do4 = findViewById(R.id.ED_do4);

        //Button
        diesel_order3 = findViewById(R.id.diesel_order3);
        online_order = findViewById(R.id.diesel_online_order3);
        DBd = new DieselOrderAdapter(this);


        diesel_order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Quantity = ED_do2.getText().toString();
                String Date = ED_do.getText().toString();
                String Time = ED_do3.getText().toString();
                String Address = ED_do4.getText().toString();

                if (Quantity.length() <= 1) {
                    if (Quantity.equals("") || Date.equals("") || Time.equals("") || Address.equals("")) {
                        Toast.makeText(Diesel_Order.this, "You miss Something please recheck once :)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Diesel_Order.this, "quantity must be 10", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Boolean insert = DBd.insertdata(Quantity, Date, Time, Address);
                    Toast.makeText(Diesel_Order.this, "Order Placed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        online_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Quantity = ED_do2.getText().toString();
                String Date = ED_do.getText().toString();
                String Time = ED_do3.getText().toString();
                String Address = ED_do4.getText().toString();
                if (Quantity.length() <= 1) {
                    if (Quantity.equals("") || Date.equals("") || Time.equals("") || Address.equals("")) {
                        Toast.makeText(Diesel_Order.this, "You miss Something please recheck once :)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Diesel_Order.this, "quantity must be 10", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Boolean insert = DBd.insertdata(Quantity, Date, Time, Address);
                    startpayment();
                    Toast.makeText(Diesel_Order.this, "Order Placed", Toast.LENGTH_SHORT).show();
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


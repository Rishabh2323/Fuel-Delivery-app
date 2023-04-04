package com.example.fuelcityapp.user;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelcityapp.Database.DBhelper;
import com.example.fuelcityapp.Database.DieselOrderAdapter;
import com.example.fuelcityapp.Database.PetrolOrderAdapter;
import com.example.fuelcityapp.R;


public class CartActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        EditText p_order;
        Button delete,p_view,update,d_view;
        PetrolOrderAdapter o;
        DieselOrderAdapter d;


        o = new PetrolOrderAdapter(this);
        d=new DieselOrderAdapter(this);
        p_order=findViewById(R.id.cart_order);
        update=findViewById(R.id.update);
        p_view= (Button) findViewById(R.id.PView);
        d_view=(Button) findViewById(R.id.DView);
        d_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor f = d.getinfo();
                if (f.getCount()==0){
                    Toast.makeText(CartActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }StringBuffer buffer=new StringBuffer();
                while (f.moveToNext())
                {
                    buffer.append("Order_ID::"+f.getString(0)+"\n");
                    buffer.append("Quantity::"+f.getString(1)+"\n");
                    buffer.append("Date::"+f.getString(2)+"\n");
                    buffer.append("Time::"+f.getString(3)+"\n");
                    buffer.append("Address::"+f.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Diesel_order_details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        p_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=o.getinfo();
                if(t.getCount()==0){
                    Toast.makeText(CartActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }StringBuffer buffer=new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("Order_ID::"+t.getString(0)+"\n");
                    buffer.append("Quantity::"+t.getString(1)+"\n");
                    buffer.append("Date::"+t.getString(2)+"\n");
                    buffer.append("Time::"+t.getString(3)+"\n");
                    buffer.append("Address::"+t.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Petrol_order_details");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l= p_order.getText().toString();
                Boolean i=d.delete_data(l);
                if(i==true)
                    Toast.makeText(CartActivity.this, "Successfull delete the order", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CartActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();


            }
        });

        delete=findViewById(R.id.del);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String d= p_order.getText().toString();
               Boolean i=o.delete_data(d);
               if(i==true)
                   Toast.makeText(CartActivity.this, "Successfull delete the order", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(CartActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();


            }
        });




    }

}
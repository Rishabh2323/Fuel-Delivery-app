package com.example.fuelcityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelcityapp.Database.Order_status;
import com.example.fuelcityapp.user.Petrol_Order;
import com.example.fuelcityapp.user.user_profile;

public class review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Button update_status,update_records;
        TextView T_order_id,T_status;
        EditText E_order_id,E_status;
        Order_status os;
        os=new Order_status(this);

        update_status=findViewById(R.id.update_status);
        T_order_id=findViewById(R.id.Torder_id);
        T_status=findViewById(R.id.Tstatus);
        E_order_id=findViewById(R.id.Eorder_id);
        E_status=findViewById(R.id.Estatus);
        update_records=findViewById(R.id.btn_update_records);

        update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Order_id=E_order_id.getText().toString();
                String status=E_status.getText().toString();

                if(Order_id.equals("")||status.equals("") )
                    Toast.makeText(review.this, "You miss Something please recheck once :)", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert= os.insertdata(Order_id,status);
                    Toast.makeText(review.this, "Order update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        update_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String statusid=E_order_id.getText().toString();
                String status=E_status.getText().toString();
                Boolean i=os.update(statusid,status);
                if(i==true)
                    Toast.makeText(review.this, "Update Succesfull", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(review.this, "Not update yet", Toast.LENGTH_SHORT).show();


            }
        });
        
        
    }
}
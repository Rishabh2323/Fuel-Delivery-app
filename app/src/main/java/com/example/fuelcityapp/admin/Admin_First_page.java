package com.example.fuelcityapp.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fuelcityapp.Database.DieselOrderAdapter;
import com.example.fuelcityapp.Database.Order_status;
import com.example.fuelcityapp.Database.PetrolOrderAdapter;
import com.example.fuelcityapp.R;
import com.example.fuelcityapp.Starting_page;
import com.example.fuelcityapp.review;
import com.example.fuelcityapp.user.Petrol_Order;
import com.example.fuelcityapp.user.user_profile;

public class Admin_First_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_first_page);

        Button manage_orders_btn, orders_sum_btn,viewdiesel;
        PetrolOrderAdapter g;
        DieselOrderAdapter h;
        h =new DieselOrderAdapter(this);
        g = new PetrolOrderAdapter(this);

        viewdiesel=findViewById(R.id.view_diesel);
        viewdiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=h.getinfo();
                if(t.getCount()==0){
                    Toast.makeText(Admin_First_page.this,"No data found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("Order_ID::"+t.getString(0)+"\n");
                    buffer.append("Quantity::"+t.getString(1)+"\n");
                    buffer.append("Date::"+t.getString(2)+"\n");
                    buffer.append("Time::"+t.getString(3)+"\n");
                    buffer.append("Address::"+t.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(Admin_First_page.this);
                builder.setCancelable(true);
                builder.setTitle("Diesel_order_details");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
       orders_sum_btn=findViewById(R.id.order_sum_btn);
        orders_sum_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(), review.class);
                startActivity(intent2);
            }
        });
        manage_orders_btn=findViewById(R.id.manage_orders_btn);
        manage_orders_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=g.getinfo();
                if(t.getCount()==0){
                    Toast.makeText(Admin_First_page.this,"No data found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("Order_ID::"+t.getString(0)+"\n");
                    buffer.append("Quantity::"+t.getString(1)+"\n");
                    buffer.append("Date::"+t.getString(2)+"\n");
                    buffer.append("Time::"+t.getString(3)+"\n");
                    buffer.append("Address::"+t.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(Admin_First_page.this);
                builder.setCancelable(true);
                builder.setTitle("Petrol_order_details");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
    
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Dont Go Back Admin:(", Toast.LENGTH_SHORT).show();
    }

    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), Starting_page.class);
                startActivity(intent);
                break;

            case R.id.profile:
            {Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
            Intent intent21=new Intent(getApplicationContext(), admin_profile.class);
            startActivity(intent21);}


            case R.id.settings:
                {Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();}break;

            case R.id.developer:
            {Toast.makeText(this, "Dev:Rishabh_Rai", Toast.LENGTH_SHORT).show();}break;
            case R.id.connect:
            {Toast.makeText(this, "@ig_rishabhrai069", Toast.LENGTH_SHORT).show();}
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
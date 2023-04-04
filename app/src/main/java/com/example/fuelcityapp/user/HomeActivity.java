package com.example.fuelcityapp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fuelcityapp.Database.Order_status;
import com.example.fuelcityapp.R;
import com.example.fuelcityapp.Starting_page;
import com.example.fuelcityapp.review;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btn_nearby;
        Button btn_order;
        Button hyper_1;
        Button cart1,orderstatus;
        Order_status os;

        os=new Order_status(this);
        orderstatus=(Button) findViewById(R.id.order_status);
        orderstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t = os.getinfo();
                if (t.getCount()==0){
                    Toast.makeText(HomeActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }StringBuffer buffer=new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("Order_ID::"+t.getString(0)+"\n");
                    buffer.append("Status::"+t.getString(1)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Order Status");
                builder.setMessage(buffer.toString());
                builder.show();
            }


        });
        cart1 = (Button) findViewById(R.id.cart);
        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcart1 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intentcart1);
            }
        });

        hyper_1 = (Button) findViewById(R.id.hyper_1);
        hyper_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hyperlinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/fuel+station/@19.2231884,73.1058513,17z"));
                startActivity(hyperlinkIntent);
            }
        });

        btn_order = (Button) findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Order.class);
                startActivity(intent2);
                                         }
                                     }
        );
    }

    public void onBackPressed() {
        Toast.makeText(this, "Dont Go Back ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu c_menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.c_menu,c_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clogout:
                Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Starting_page.class);
                startActivity(intent);
                break;
            case R.id.cprofile:
            {Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();}
            Intent intent2=new Intent(getApplicationContext(),user_profile.class);
            startActivity(intent2);
            break;

            case R.id.csettings:
            {Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();}break;

            case R.id.cdeveloper:
            {Toast.makeText(this, "Dev:Rishabh_Rai", Toast.LENGTH_SHORT).show();}break;
            case R.id.cconnect:
            {Toast.makeText(this, "@ig_rishabhrai069", Toast.LENGTH_SHORT).show();}
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}




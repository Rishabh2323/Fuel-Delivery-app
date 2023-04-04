package com.example.fuelcityapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelcityapp.Database.DBhelper;
import com.example.fuelcityapp.R;

public class Client_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username1,password1;
        Button btn21,btn22;
        DBhelper DB;


        username1=(EditText) findViewById(R.id.username1);
        password1=(EditText) findViewById(R.id.password1);
        btn21=(Button) findViewById(R.id.btn21);
        btn22=(Button) findViewById(R.id.btn22);
        DB = new DBhelper(this);
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1  = new Intent(getApplicationContext(), Resigter_page.class);
                startActivity(intent1);
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =username1.getText().toString();
                String pass =password1.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Client_login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Client_login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Client_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
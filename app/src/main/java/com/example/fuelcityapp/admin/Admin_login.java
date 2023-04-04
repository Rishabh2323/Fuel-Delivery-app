package com.example.fuelcityapp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelcityapp.Database.Admin_DBhelper;
import com.example.fuelcityapp.R;

public class Admin_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        EditText admin_username, admin_password;
        Button admin_signin_btn, admin_register_btn;
        Admin_DBhelper DB;

        admin_username = findViewById(R.id.admin_username);
        admin_password = findViewById(R.id.admin_password);
        admin_signin_btn=findViewById(R.id.admin_signin_btn);
        admin_register_btn=findViewById(R.id.admin_register_btn);
        DB = new Admin_DBhelper(this);
        admin_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(getApplicationContext(), Admin_Resigter_page.class);
                startActivity(intent3);
            }
        });

        admin_signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =admin_username.getText().toString();
                String pass =admin_password.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Admin_login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Admin_login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Admin_First_page.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Admin_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}



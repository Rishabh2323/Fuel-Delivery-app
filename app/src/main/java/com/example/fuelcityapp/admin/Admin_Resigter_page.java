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

public class Admin_Resigter_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_resigter_page);
        EditText AdminR_username, AdminR_password, AdminR_retype,admin_company,Email_id,Phone_number;
        Button AdminR_btn1, AdminR_btn2;
        Admin_DBhelper DB;

        AdminR_username = (EditText) findViewById(R.id.AdminR_username);
        Email_id = (EditText) findViewById(R.id.Email_id);
        Phone_number = (EditText) findViewById(R.id.Phone_number);
        admin_company=(EditText) findViewById(R.id.admin_company);
        AdminR_password = (EditText) findViewById(R.id.adminR_password);
        AdminR_retype = (EditText) findViewById(R.id.adminR_retype);
        AdminR_btn1 = (Button) findViewById(R.id.adminR_btn1);
        AdminR_btn2 = (Button) findViewById(R.id.adminR_btn2);
        DB = new Admin_DBhelper(this);

        AdminR_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = AdminR_username.getText().toString();
                String pass = AdminR_password.getText().toString();
                String repass = AdminR_retype.getText().toString();
                String comp = admin_company.getText().toString();
                String email = Email_id.getText().toString();
                String phone = Phone_number.getText().toString();





                if(user.equals("")||pass.equals("")||repass.equals("")||comp.equals("")||email.equals("")||phone.equals(""))
                    Toast.makeText(Admin_Resigter_page.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.length()>=8) {
                    if (email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                        if (phone.matches("[0-9]{10}$")) {
                            if (comp.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)+(\\s[a-zA-Z]+)?$")) {
                                if (pass.equals(repass)) {
                                    Boolean checkuser = DB.checkusername(user);
                                    if (checkuser == false) {
                                        Boolean insert = DB.insertdata(user, pass, comp, email, phone);
                                        if (insert == true) {
                                            Toast.makeText(Admin_Resigter_page.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Admin_First_page.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(Admin_Resigter_page.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(Admin_Resigter_page.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Admin_Resigter_page.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                admin_company.requestFocus();
                                admin_company.setError("Enter Your company name \n Ex:-abc xyz ");
                            }
                        }
                        else{
                            Phone_number.requestFocus();
                            Phone_number.setError("Enter 10 Digit valid Phone Number\n Ex:-9999000099");
                        }
                        }
                    else{
                        Email_id.requestFocus();
                        Email_id.setError("Enter valid Email");
                    } }else{
                        AdminR_password.requestFocus();
                        AdminR_password.setError("Password must be 8 digit");
                    }
                }
            }
        });
        AdminR_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin_login.class);
                startActivity(intent);

            }
        });

    }
}
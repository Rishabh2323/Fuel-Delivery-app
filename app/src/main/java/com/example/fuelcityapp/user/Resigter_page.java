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

public class Resigter_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigter_page);
        EditText username, password, retype, useremail, userphne, usercompany, usergst;
        Button btn1, btn2;
        DBhelper DB;

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        retype = (EditText) findViewById(R.id.retype);
        useremail= (EditText) findViewById(R.id.useremail);
        userphne= (EditText) findViewById(R.id.userphne);
        usercompany= (EditText) findViewById(R.id.usercompany);
        usergst= (EditText) findViewById(R.id.userGST);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);


        DB = new DBhelper(this);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = retype.getText().toString();
                String gst = usergst.getText().toString();
                String ucompany = usercompany.getText().toString();
                String uphne = userphne.getText().toString();
                String uemail = useremail.getText().toString();


                if(user.equals("")||pass.equals("")||repass.equals("")||uemail.equals("")||ucompany.equals("")||uphne.equals(""))
                    Toast.makeText(Resigter_page.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.length()>=8) {
                        if (uemail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                            if (uphne.matches("[0-9]{10}$")) {
                                if (ucompany.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)+(\\s[a-zA-Z]+)?$")) {
                                    if (pass.equals(repass)) {
                                        Boolean checkuser = DB.checkusername(user);
                                        if (checkuser == false) {
                                            Boolean insert = DB.insertdata(user, pass, gst, ucompany, uphne, uemail);
                                            if (insert == true) {
                                                Toast.makeText(Resigter_page.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(Resigter_page.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(Resigter_page.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(Resigter_page.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    usercompany.requestFocus();
                                    usercompany.setError("Enter Your company name \n Ex:-abc xyz ");
                                }
                            } else {
                                userphne.requestFocus();
                                userphne.setError("Enter 10 Digit valid Phone Number\n Ex:-9999000099");
                            }
                        } else {
                            useremail.requestFocus();
                            useremail.setError("Enter valid Email");
                        }
                    }else{
                        password.requestFocus();
                        password.setError("Password must be 8 digit");
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Client_login.class);
                startActivity(intent);

            }
        });

    }
}
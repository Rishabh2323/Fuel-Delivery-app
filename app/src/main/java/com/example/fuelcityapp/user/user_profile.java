package com.example.fuelcityapp.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fuelcityapp.Database.DBhelper;
import com.example.fuelcityapp.R;

public class user_profile extends AppCompatActivity {
    EditText ud_username, ud_pass, ud_comp,ud_gst,ud_phone,ud_email;
    Button update;
    DBhelper MYDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ud_username=findViewById(R.id.uun);
        ud_pass=findViewById(R.id.update_password);
        ud_comp=findViewById(R.id.update_usercompany);
        ud_gst=findViewById(R.id.CGST);
        ud_phone=findViewById(R.id.uPhone);
        ud_email=findViewById(R.id.uemail);
        update=findViewById(R.id.update);
        MYDB=new DBhelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ud_username.getText().toString();
                String password = ud_pass.getText().toString();
                String company = ud_comp.getText().toString();
                String Gst = ud_gst.getText().toString();
                String Phone = ud_phone.getText().toString();
                String Email = ud_email.getText().toString();
                if (Email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    if (Phone.matches("[0-9]{10}$")) {
                        if (company.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)+(\\s[a-zA-Z]+)?$")) {
                            if (password.length() >= 8) {
                                Boolean i = MYDB.update_data(name, password, company, Gst, Phone, Email);
                                if (i == true)
                                    Toast.makeText(user_profile.this, "Update Succesfull", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(user_profile.this, "Not update yet", Toast.LENGTH_SHORT).show();
                            } else {
                                ud_pass.requestFocus();
                                ud_pass.setError("Password must 8 digit");
                            }
                        } else {
                            ud_comp.requestFocus();
                            ud_comp.setError("Enter Your company name \n Ex:-abc xyz ");
                        }
                    } else {
                        ud_phone.requestFocus();
                        ud_phone.setError("Enter 10 Digit valid Phone Number\n Ex:-9999000099");
                    }
                }
                else{
                    ud_email.requestFocus();
                    ud_email.setError("Enter valid Email");
                }
            }

        });
    }
}

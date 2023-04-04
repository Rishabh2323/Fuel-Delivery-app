package com.example.fuelcityapp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelcityapp.Database.Admin_DBhelper;
import com.example.fuelcityapp.Database.DBhelper;
import com.example.fuelcityapp.R;


public class admin_profile extends AppCompatActivity {

    EditText aud_username, aud_pass, aud_comp,aud_phone,aud_email;
    Button aupdate;
    Admin_DBhelper ADMINDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        aud_username=findViewById(R.id.auun);
        aud_pass=findViewById(R.id.aupdate_password);
        aud_comp=findViewById(R.id.aupdate_usercompany);
        aud_phone=findViewById(R.id.APhone);
        aud_email=findViewById(R.id.Aemailid);
        aupdate=findViewById(R.id.aupdate);

        ADMINDB= new Admin_DBhelper(this);

        aupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aname=aud_username.getText().toString();
                String apassword=aud_pass.getText().toString();
                String acompany=aud_comp.getText().toString();
                String Aphone=aud_phone.getText().toString();
                String AEmail=aud_email.getText().toString();

                Boolean i=ADMINDB.adminupdate(aname,apassword,acompany,Aphone,AEmail);
                if(i==true)
                    Toast.makeText(admin_profile.this, "Update Succesfull", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(admin_profile.this, "Not update yet", Toast.LENGTH_SHORT).show();
            }

        });
    }
}

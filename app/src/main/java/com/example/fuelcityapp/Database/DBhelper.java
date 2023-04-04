package com.example.fuelcityapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBname="Login.db";  //name set of database

    public DBhelper(Context context) {
        super(context, "Login.db", null, 1);
    } //const of sqlite


    @Override
    public void onCreate(SQLiteDatabase MYDB) {                 //oncreate method for create an db name as MYDB
    MYDB.execSQL("create Table users(username TEXT primary key, password TEXT,userGST TEXT,usercompany TEXT,useremail TEXT,userphne TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MYDB, int i, int i1) {         //onupgrade method is use for any update in db
    MYDB.execSQL("drop  table if exists users");
    }

    public boolean insertdata(String username, String password, String usergst, String usercompany,String userphne,String useremail) {   // Inserting username and password to SQLITE TABLE
        SQLiteDatabase MYDB=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("usergst",usergst);
        contentValues.put("usercompany",usercompany);
        contentValues.put("userphne",userphne);
        contentValues.put("useremail",useremail);
        long result = MYDB.insert("Users",null,contentValues);
        if (result==1)return false;
        else
            return true ;
    }
    public boolean checkusername(String username) {                     // checking user name
        SQLiteDatabase MYDB = this.getReadableDatabase();
        Cursor cursor = MYDB.rawQuery("Select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String password) {     //checking passwd
        SQLiteDatabase MYDB = this.getReadableDatabase();
        Cursor cursor = MYDB.rawQuery("Select * from users where username=? and password=?", new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean update_data(String username, String password, String usercompany,String usergst,String userphne,String useremail)
    {
        SQLiteDatabase MYDB=this.getWritableDatabase();
        ContentValues c =new ContentValues();
        c.put("password",password);
        c.put("usercompany",usercompany);
        c.put("usergst",usergst);
        c.put("userphne",userphne);
        c.put("useremail",useremail);
        Cursor cursor = MYDB.rawQuery("Select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0) {
            long r = MYDB.update("users", c, "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;
        }
        else
            return false;

    }


}

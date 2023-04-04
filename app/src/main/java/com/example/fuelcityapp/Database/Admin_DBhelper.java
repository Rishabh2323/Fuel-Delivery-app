package com.example.fuelcityapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Admin_DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME="admin.db";

    public Admin_DBhelper(Context context) {
        super(context,"admin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase ADMINDB) {
        ADMINDB.execSQL("create Table admins (username TEXT primary key, password  TEXT,company TEXT,Email_id TEXT,Phone_number TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase ADMINDB, int i, int i1) {
        ADMINDB.execSQL("drop Table if exists admins");
        }
    public boolean insertdata(String username, String password, String company, String Email_id, String Phone_number){
        SQLiteDatabase ADMINDB=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("Company",company);
        contentValues.put("Email_id",Email_id);
        contentValues.put("Phone_number",Phone_number);

        long result = ADMINDB.insert("admins",null,contentValues);
        if (result==1)return false;
        else
            return true ;
    }
    public boolean checkusername(String username) {                     // checking user name
        SQLiteDatabase ADMINDB = this.getReadableDatabase();
        Cursor cursor = ADMINDB.rawQuery("Select * from admins where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String password) {     //checking passwd
        SQLiteDatabase ADMINDB = this.getReadableDatabase();
        Cursor cursor = ADMINDB.rawQuery("Select * from admins where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean adminupdate(String username, String password, String company,String Phone_number, String Email_id )
    {
        SQLiteDatabase ADMINDB=this.getWritableDatabase();
        ContentValues a =new ContentValues();
        a.put("password",password);
        a.put("company",company);
        a.put("Phone_number",Phone_number);
        a.put("Email_id",Email_id);
        Cursor cursor = ADMINDB.rawQuery("Select * from admins where username=?",new String[]{username});
        if (cursor.getCount()>0) {
            long  m = ADMINDB.update("admins", a, "username=?", new String[]{username});
            if (m == -1) return false;
            else
                return true;
        }
        else
            return false;

    }


}
package com.example.fuelcityapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Order_status extends SQLiteOpenHelper {

    public static final String DBNAME="order_status";
    public Order_status(Context context) {
        super(context,"order_status.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Status) {
        Status.execSQL("create Table order_status (order_id TEXT primary key,status TEXT)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase Status, int i, int i1) {
        Status.execSQL("drop Table if exists order_status");
    }


    public boolean insertdata(String order_id ,String status){
        SQLiteDatabase Status=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id",order_id);
        contentValues.put("status",status);

        long result1 = Status.insert("order_status",null,contentValues);
        if (result1==1)return false;
        else
            return true ;
    }
    public Cursor getinfo() {
        SQLiteDatabase os = this.getWritableDatabase();
        Cursor cursor = os.rawQuery("select * from order_status", null);
        return cursor;
    }
    public boolean update(String order_id,String status){
        SQLiteDatabase Status=this.getWritableDatabase();
        ContentValues c =new ContentValues();
        c.put("status",status);
        Cursor cursor = Status.rawQuery("Select * from order_status where order_id=?",new String[]{order_id});
        if (cursor.getCount()>0) {
            long f = Status.update("order_status",c, "order_id=?", new String[]{order_id});
            if (f == -1) return false;
            else
                return true;
        }
        else
            return false;
    }

}

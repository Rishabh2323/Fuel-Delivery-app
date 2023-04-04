package com.example.fuelcityapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DieselOrderAdapter extends SQLiteOpenHelper {

    public static final String DBNAME = "Diesel_orders.db";

    public DieselOrderAdapter(Context context) {
        super(context, "Diesel_order.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase orderDB) {
        orderDB.execSQL("create Table Diesel_order (_id INTEGER PRIMARY KEY AUTOINCREMENT,Quantity TEXT,Date TEXT,Time TEXT,Address TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase orderDB, int i, int i1) {
        orderDB.execSQL("drop Table if exists Diesel_order");
    }

    public boolean insertdata(String Quantity, String Date, String Time, String Address) {
        SQLiteDatabase orderDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Quantity", Quantity);
        contentValues.put("Date", Date);
        contentValues.put("Time", Time);
        contentValues.put("Address", Address);

        long result = orderDB.insert("Diesel_order",null,contentValues);
        if (result==1)return false;
        else
            return true ;

    }
    public Cursor getinfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Diesel_order", null);
        return cursor;
    }
    public boolean delete_data(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from Diesel_order where _id=?", new String[]{_id});

        if (cursor.getCount() > 0) {
            long v = db.delete("Diesel_order", "_id=?", new String[]{_id});
            if (v == -1) return false;
            else
                return true;
        } else {
            return false;
        }


    }
}

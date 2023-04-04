package com.example.fuelcityapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class PetrolOrderAdapter extends SQLiteOpenHelper {

    public static final String DBNAME = "Petrol_orders.db";

    public PetrolOrderAdapter(Context context) {
        super(context, "Petrol_order.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase orderDB) {
        orderDB.execSQL("create Table Petrol_order (_id INTEGER PRIMARY KEY AUTOINCREMENT,Quantity TEXT,Date TEXT,Time TEXT,Address TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase orderDB, int i, int i1) {
        orderDB.execSQL("drop Table if exists Petrol_order");
    }

    public boolean insertdata(String Quantity, String Date, String Time, String Address) {
        SQLiteDatabase orderDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Quantity", Quantity);
        contentValues.put("Date", Date);
        contentValues.put("Time", Time);
        contentValues.put("Address", Address);

        long result = orderDB.insert("Petrol_order", null, contentValues);
        if (result == 1) return false;
        else
            return true;

    }

    public Cursor getinfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Petrol_order", null);
        return cursor;
    }

    public boolean delete_data(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from Petrol_order where _id=?", new String[]{_id});

        if (cursor.getCount() > 0) {
            long v = db.delete("Petrol_order", "_id=?", new String[]{_id});
            if (v == -1) return false;
            else
                return true;
        } else {
            return false;
        }


    }
}

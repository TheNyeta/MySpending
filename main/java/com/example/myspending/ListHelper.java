package com.example.myspending;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ListHelper {
    DatabaseHelper helper;
    public ListHelper(Context context){
        helper = new DatabaseHelper(context);

    }

    public void insertList(String name, int price){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO List(name, price, date) VALUES(" +
                "'"+name+"', '"+price+"', '"+date+"')");
        db.close();
        helper.close();
    }

    public void editList(int id, String name, int price){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE List SET name = '"+name+"', price = '"+price+"' WHERE id = '"+id+"'");
        db.close();
        helper.close();
    }

    public void deleteList(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM List WHERE id = '"+id+"'");
        db.close();
        helper.close();
    }

    public Vector<SpendingList> getAllData(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM List", null);
        cursor.moveToFirst();

        Vector<SpendingList> vlist = null;
        if(cursor.getCount() > 0){
            vlist = new Vector<>();
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                vlist.add(new SpendingList(id, name, price, date));
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();
        helper.close();
        return vlist;

    }

}

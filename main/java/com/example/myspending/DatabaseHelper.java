package com.example.myspending;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper( Context context) {
        super(context, "SpendingList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE List(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT(30) NOT NULL," +
                "price INTEGER NOT NULL," +
                "date TEXT(30) NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS List");
        onCreate(db);
    }
}

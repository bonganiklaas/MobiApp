package com.example.bongani.mobiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by BONGANI on 1/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Customer.db";
    public static final String TABLE_NAME = "customer_table";
    public static final String COLUMN_1  = "ID";
    public static final String COLUMN_2 = "NAME";
    public static final String COLUMN_3 = "SURNAME";
    public static final String COLUMN_4 = "AGE";


    /**
     *   my constructor -WHEN THIS CONSTRUCTOR IS CALLED THE DATABASE IS CREATED
     *   creating a database and checking database
      */

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, AGE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public  boolean insertData( String name, String surname, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, name);
        contentValues.put(COLUMN_3, surname);
        contentValues.put(COLUMN_4, age);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if ( result == -1)
            return  false;
        else
            return true;
    }
}

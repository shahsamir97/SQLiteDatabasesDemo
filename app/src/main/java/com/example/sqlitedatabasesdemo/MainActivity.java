package com.example.sqlitedatabasesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("Users", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS thenewUsers (name varchar, age INT(3), id INTEGER PRIMARY KEY)");
            sqLiteDatabase.execSQL("INSERT INTO thenewUsers (name, age) values ('Nick',23)");
            sqLiteDatabase.execSQL("INSERT INTO thenewUsers (name, age) values ('Nick',14)");
            sqLiteDatabase.execSQL("INSERT INTO thenewUsers (name, age) values ('Dave',10)");
            sqLiteDatabase.execSQL("DELETE FROM thenewUsers where id = 2");
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thenewUsers", null);
            int nameIndex = cursor.getColumnIndex("name");
            int yearIndex = cursor.getColumnIndex("age");
            int idIndex =  cursor.getColumnIndex("id");
            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("Name : ", cursor.getString(nameIndex));
                Log.i("Age  : ", Integer.toString(cursor.getInt(yearIndex)));
                Log.i("ID : ", Integer.toString(cursor.getInt(idIndex)));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

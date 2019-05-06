package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table usuario (idusuario integer primary key autoincrement not null, usuario text, pass text)");

        /*ContentValues cv = new ContentValues();
        cv.put("nickname", "Dieko");
        cv.put("puntuacion", "5");
        BaseDeDatos.insert("jugadores", null, cv);
        BaseDeDatos.close();*/


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
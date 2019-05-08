package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String dataname = "ReWriteDataBase";


    public AdminSQLiteOpenHelper(Context context) {
        super(context, dataname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table usuario (idusuario integer primary key autoincrement not null, usuario text, pass text,tipo text)");
       // BaseDeDatos.execSQL("create table obra (idobra integer primary key autoincrement not null, titulo text, fecha text,tipo text," +
               // "idusuario1 integer, FOREIGN KEY (idusuario1) REFERENCES usuario(idusuario))");
        BaseDeDatos.execSQL("create table lienzo (idlienzo integer primary key autoincrement not null, titulolienzo text,contenido text,idusuario1 " +
                "integer, FOREIGN KEY (idusuario1) REFERENCES usuario(idusuario))");
        BaseDeDatos.execSQL("create table ensayo (idensayo integer primary key autoincrement not null,tituloensayo text, intro text, desarrollo text, " +
                "conclusion text, referencias text, idusuario2 integer, " +
                " FOREIGN KEY (idusuario2) REFERENCES usuario(idusuario))");
        BaseDeDatos.execSQL("create table reporte (idreporte integer primary key autoincrement not null," +
                " idlevanta integer, idasignado integer, idsoluciona integer, idcierra integer," +
                " fechaLevanta text, fechaAsigna text, fechaSoluciona text, fechaCierra text," +
                " etiqueta text, estado text, problema text, solucion text," +
                " FOREIGN KEY (idlevanta) REFERENCES usuario(idusuario), " +
                " FOREIGN KEY (idasignado) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idsoluciona) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idcierra) REFERENCES usuario(idusuario))" +
                "");



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
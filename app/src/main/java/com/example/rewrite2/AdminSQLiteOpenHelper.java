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
        BaseDeDatos.execSQL("create table reporteE (idreporteE integer primary key autoincrement not null," +
                " idlevanta integer, idasignado integer, idsoluciona integer, idcierra integer," +
                " fechaLevanta text," +
                " etiqueta text, estado text, problema text, solucion text," +
                " FOREIGN KEY (idlevanta) REFERENCES usuario(idusuario), " +
                " FOREIGN KEY (idasignado) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idsoluciona) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idcierra) REFERENCES usuario(idusuario))" +
                "");
        BaseDeDatos.execSQL("create table reporteM (idreporteM integer primary key autoincrement not null," +
                " idlevanta1 integer, idasignado1 integer, idsoluciona1 integer, idcierra1 integer," +
                " fechaLevanta text, fechaAsigna text, fechaSoluciona text, fechaCierra text," +
                " etiqueta text, estado text, problema text, solucion text, idrepEvento integer," +
                " FOREIGN KEY (idlevanta1) REFERENCES usuario(idusuario), " +
                " FOREIGN KEY (idasignado1) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idsoluciona1) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idcierra1) REFERENCES usuario(idusuario)," +
                " FOREIGN KEY (idrepEvento) REFERENCES reporteE(idreporteE))" +
                "");
        BaseDeDatos.execSQL("create table FAQs (idFAQ integer primary key autoincrement not null," +
                " idlevanta2 integer, pregunta text, idrepEvento1 integer, solucion text, etiqueta text," +
                " FOREIGN KEY (idrepEvento1) REFERENCES reporteE(idreporteE),"+
                " FOREIGN KEY (idlevanta2) REFERENCES usuario(idusuario))"+
                "");

        ContentValues nuevoRegistro1, nuevoRegistro2, nuevoRegistro3, nuevoRegistro4, nuevoRegistro5, nuevoRegistro6;

        nuevoRegistro1 = new ContentValues();
        nuevoRegistro1.put("usuario", "Marco");
        nuevoRegistro1.put("pass", "123");
        nuevoRegistro1.put("tipo", "editor");
        BaseDeDatos.insert("usuario", null, nuevoRegistro1);

        nuevoRegistro2 = new ContentValues();
        nuevoRegistro2.put("usuario", "Hugo");
        nuevoRegistro2.put("pass", "123");
        nuevoRegistro2.put("tipo", "operador");
        BaseDeDatos.insert("usuario", null, nuevoRegistro2);

        nuevoRegistro3 = new ContentValues();
        nuevoRegistro3.put("usuario", "Pedro");
        nuevoRegistro3.put("pass", "123");
        nuevoRegistro3.put("tipo", "ingenieroE");
        BaseDeDatos.insert("usuario", null, nuevoRegistro3);

        nuevoRegistro4 = new ContentValues();
        nuevoRegistro4.put("usuario", "Bruno");
        nuevoRegistro4.put("pass", "123");
        nuevoRegistro4.put("tipo", "ingenieroM");
        BaseDeDatos.insert("usuario", null, nuevoRegistro4);

        nuevoRegistro5 = new ContentValues();
        nuevoRegistro5.put("usuario", "Aldo");
        nuevoRegistro5.put("pass", "123");
        nuevoRegistro5.put("tipo", "gerenteM");
        BaseDeDatos.insert("usuario", null, nuevoRegistro5);

        nuevoRegistro6 = new ContentValues();
        nuevoRegistro6.put("usuario", "Juan");
        nuevoRegistro6.put("pass", "123");
        nuevoRegistro6.put("tipo", "gerenteE");
        BaseDeDatos.insert("usuario", null, nuevoRegistro6);




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
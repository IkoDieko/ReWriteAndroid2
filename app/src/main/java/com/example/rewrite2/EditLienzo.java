package com.example.rewrite2;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditLienzo extends AppCompatActivity {
    private EditText titulo, contenido;
    private FloatingActionButton salir, guardar, elimina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lienzo);

        titulo = findViewById(R.id.actualizaTituloLienzo);
        contenido = findViewById(R.id.actualizaContenidoLienzo);
        salir = findViewById(R.id.salirActualizaLienzo);
        guardar = findViewById(R.id.actualizaLienzo);
        elimina = findViewById(R.id.elimiaLienzo);

        Intent i = getIntent();
        String id = i.getStringExtra("idusuario3");
        String tituloA = i.getStringExtra("TituloL");

        AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(EditLienzo.this);
        SQLiteDatabase bd = buscar.getWritableDatabase();

        Cursor validar = bd.rawQuery("select * from lienzo where idusuario1 = '"+id+"' and titulolienzo = '"+tituloA+"'", null);
        if (validar.moveToFirst()){
            String contenidoA = validar.getString(validar.getColumnIndex("contenido"));
            contenido.setText(contenidoA);
            titulo.setText(tituloA);
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String id = i.getStringExtra("idusuario3");
                String tituloA = i.getStringExtra("TituloL");
                String tituloN = titulo.getText().toString();
                String conteN = contenido.getText().toString();

                AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(EditLienzo.this);
                SQLiteDatabase bd = buscar.getWritableDatabase();

                Cursor validar = bd.rawQuery("select * from lienzo where idusuario1 = '"+id+"' and titulolienzo = '"+tituloA+"'", null);
                if (validar.moveToFirst()){

                    String idLienzo = validar.getString(validar.getColumnIndex("idlienzo"));
                    Cursor validar2 = bd.rawQuery("select * from lienzo where idusuario1 = '"+id+"' and titulolienzo = '"+tituloN+"'", null);
                    if (validar2.moveToFirst()){
                        Cursor validar3 = bd.rawQuery("select * from lienzo where idlienzo = '"+idLienzo+"' and titulolienzo = '"+tituloN+"'", null);
                        if (validar3.moveToNext()){
                            SQLiteDatabase BaseDeDatos = buscar.getWritableDatabase();
                            ContentValues actualizaLienzo = new ContentValues();
                            actualizaLienzo.put("contenido", conteN);
                            bd.update("lienzo", actualizaLienzo, "idlienzo =" +idLienzo, null);
                            Toast.makeText(EditLienzo.this, "Datos guardados.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(EditLienzo.this, "Ya tienes otro lienzo con este nombre.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        SQLiteDatabase BaseDeDatos = buscar.getWritableDatabase();
                        ContentValues actualizaLienzo = new ContentValues();
                        actualizaLienzo.put("titulolienzo", tituloN);
                        actualizaLienzo.put("contenido", conteN);
                        bd.update("lienzo", actualizaLienzo, "idlienzo =" +idLienzo, null);
                        Toast.makeText(EditLienzo.this, "Datos guardados.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(EditLienzo.this);
                alerta.setMessage("¿Desea eliminar este lienzo?").setCancelable(false);
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(EditLienzo.this);
                        SQLiteDatabase bd = buscar.getWritableDatabase();
                        Intent i = getIntent();
                        String id = i.getStringExtra("idusuario3");
                        String tituloA = i.getStringExtra("TituloL");
                        Cursor validar = bd.rawQuery("select * from lienzo where idusuario1 = '"+id+"' and titulolienzo = '"+titulo.getText().toString()+"'", null);
                        if (validar.moveToFirst()){
                            String idL = validar.getString(validar.getColumnIndex("idlienzo"));
                            bd.delete("lienzo", "idlienzo="+idL, null);
                            Toast.makeText(EditLienzo.this, "Lienzo eliminado.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("¡Cuidado! Estás por eliminar tu lienzo.");
                titulo.show();
            }
        });
    }
}

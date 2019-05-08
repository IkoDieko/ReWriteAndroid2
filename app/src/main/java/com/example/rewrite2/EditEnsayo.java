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
import android.widget.TextView;
import android.widget.Toast;

public class EditEnsayo extends AppCompatActivity {
    private TextView titulo, intro, desa, conc, refe;
    private FloatingActionButton salir, guardar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ensayo);

        titulo = findViewById(R.id.actualizaTituloEnsayo);
        intro = findViewById(R.id.actualizaIntro);
        desa = findViewById(R.id.actualizaDesa);
        conc = findViewById(R.id.actualizaConc);
        refe = findViewById(R.id.actualizaRefe);
        salir = findViewById(R.id.salirActualizaEnsayo);
        guardar = findViewById(R.id.actualizaEnsayo);
        eliminar = findViewById(R.id.eliminaEnsayo);

        Intent i = getIntent();
        String id = i.getStringExtra("idusuario4");
        String tituloA = i.getStringExtra("TituloE");


        AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(EditEnsayo.this);
        SQLiteDatabase bd = buscar.getWritableDatabase();

        Cursor validar = bd.rawQuery("select * from ensayo where idusuario2 = '"+id+"' and tituloensayo = '"+tituloA+"'", null);
        if (validar.moveToFirst()){
            String introA = validar.getString(validar.getColumnIndex("intro"));
            String desaA = validar.getString(validar.getColumnIndex("desarrollo"));
            String concA = validar.getString(validar.getColumnIndex("conclusion"));
            String refeA = validar.getString(validar.getColumnIndex("referencias"));
            intro.setText(introA);
            desa.setText(desaA);
            conc.setText(concA);
            refe.setText(refeA);
            titulo.setText(tituloA);
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String id = i.getStringExtra("idusuario4");
                String tituloA = i.getStringExtra("TituloE");
                String tituloN = titulo.getText().toString();
                String introN = intro.getText().toString();
                String desaN = desa.getText().toString();
                String concN = conc.getText().toString();
                String refeN = refe.getText().toString();

                AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(EditEnsayo.this);
                SQLiteDatabase bd = buscar.getWritableDatabase();

                Cursor validar = bd.rawQuery("select * from ensayo where idusuario2 = '" + id + "' and tituloensayo= '" + tituloA + "'",
                        null);
                if (validar.moveToFirst()) {

                    String idEnsayo = validar.getString(validar.getColumnIndex("idensayo"));
                    Cursor validar2 = bd.rawQuery("select * from ensayo where idusuario2 = '" + id + "' and tituloensayo= '" + tituloN + "'",
                            null);
                    if (validar2.moveToFirst()) {
                        Cursor validar3 = bd.rawQuery("select * from ensayo where idensayo = '" + idEnsayo + "' and tituloensayo= '" + tituloN + "'",
                                null);
                        if (validar3.moveToNext()) {
                            SQLiteDatabase BaseDeDatos = buscar.getWritableDatabase();
                            ContentValues actualizaLienzo = new ContentValues();
                            actualizaLienzo.put("intro", introN);
                            actualizaLienzo.put("desarrollo", desaN);
                            actualizaLienzo.put("conclusion", concN);
                            actualizaLienzo.put("referencias", refeN);
                            bd.update("ensayo", actualizaLienzo, "idensayo =" + idEnsayo, null);
                            Toast.makeText(EditEnsayo.this, "Datos guardados.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditEnsayo.this, "Ya tienes otro ensayo con este nombre.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        SQLiteDatabase BaseDeDatos = buscar.getWritableDatabase();
                        ContentValues actualizaLienzo = new ContentValues();
                        actualizaLienzo.put("tituloensayo", tituloN);
                        actualizaLienzo.put("intro", introN);
                        actualizaLienzo.put("desarrollo", desaN);
                        actualizaLienzo.put("conclusion", concN);
                        actualizaLienzo.put("referencias", refeN);
                        bd.update("ensayo", actualizaLienzo, "idensayo =" + idEnsayo, null);
                        Toast.makeText(EditEnsayo.this, "Datos guardados.", Toast.LENGTH_SHORT).show();
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(EditEnsayo.this);
                alerta.setMessage("¿Desea eliminar este ensayo?").setCancelable(false);
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(EditEnsayo.this);
                        SQLiteDatabase bd = buscar.getWritableDatabase();
                        Intent i = getIntent();
                        String id = i.getStringExtra("idusuario4");
                        String tituloA = i.getStringExtra("TituloE");
                        Cursor validar = bd.rawQuery("select * from ensayo where idusuario2 = '"+id+"' and tituloensayo= '"+titulo.getText().toString()+"'",
                                null);
                        if (validar.moveToFirst()){
                            String idL = validar.getString(validar.getColumnIndex("idensayo"));
                            bd.delete("ensayo", "idensayo="+idL, null);
                            Toast.makeText(EditEnsayo.this, "Ensayo eliminado.", Toast.LENGTH_SHORT).show();
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
                titulo.setTitle("¡Cuidado! Estás por eliminar tu ensayo.");
                titulo.show();

            }
        });
    }
}

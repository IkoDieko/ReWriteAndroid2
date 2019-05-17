package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AltaFAQCero extends AppCompatActivity {
    private Button btnGuardar;
    private EditText txtPregunta, txtRespuesta, txtEtiqueta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_faqcero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGuardar = (Button) findViewById(R.id.btnGuarda);
        txtEtiqueta = (EditText) findViewById(R.id.editEti);
        txtPregunta = (EditText) findViewById(R.id.editPre);
        txtRespuesta = (EditText) findViewById(R.id.editRes);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtPregunta.getText().toString().equals("")&&!txtEtiqueta.getText().toString().equals("")
                        &&!txtRespuesta.getText().toString().equals("")){
                    AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(AltaFAQCero.this);
                    SQLiteDatabase bd = alta.getWritableDatabase();

                    String eti = txtEtiqueta.getText().toString();
                    String pre = txtPregunta.getText().toString();
                    String res = txtRespuesta.getText().toString();

                    Intent miInt = getIntent();
                    String us = miInt.getStringExtra("idusuario");

                    Cursor buscar = bd.rawQuery("select * from FAQs where idlevanta2 = "+us, null);
                    while (buscar.moveToNext()){
                        System.out.println("FAQ: "+buscar.getString(buscar.getColumnIndex("pregunta")));
                    }

                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("pregunta", pre);
                    nuevoRegistro.put("solucion", res);
                    nuevoRegistro.put("etiqueta", eti);
                    nuevoRegistro.put("idlevanta2", us);
                    bd.insert("FAQs", null, nuevoRegistro);
                    bd.close();

                    Toast.makeText(AltaFAQCero.this, "¡FAQ añadida!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AltaFAQCero.this, "¡Falta llenar algún campo!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

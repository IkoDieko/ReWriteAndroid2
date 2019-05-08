package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

public class Lienzo extends AppCompatActivity {
    private EditText contenido, titulo;
    private FloatingActionButton guardar, regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienzo);

        titulo = findViewById(R.id.editTituloLienzo);
        contenido = findViewById(R.id.editContenidoLienzo);
        guardar = findViewById(R.id.guardarLienzo);
        regresar = findViewById(R.id.salirLienzo);

        contenido.setMovementMethod(new ScrollingMovementMethod());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String id =intent.getStringExtra("idusuario2");
                String Conte;
                String Tit;

                Conte = contenido.getText().toString();
                Tit = titulo.getText().toString();

                AdminSQLiteOpenHelper soporte = new AdminSQLiteOpenHelper(Lienzo.this);
                SQLiteDatabase bd = soporte.getWritableDatabase();
                Cursor buscar = bd.rawQuery("select * from usuario where idusuario = '"+id+"'", null);
                if (buscar.moveToFirst()){
                    SQLiteDatabase BaseDeDatos = soporte.getWritableDatabase();

                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("titulolienzo", Tit);
                    nuevoRegistro.put("contenido", Conte);
                    nuevoRegistro.put("idusuario1", id);
                    BaseDeDatos.insert("lienzo", null, nuevoRegistro);

                    Toast.makeText(Lienzo.this,"Obra guardada.", Toast.LENGTH_SHORT).show();

                    finish();

                    //String usuariocreador = buscar.getString(buscar.getColumnIndex("usuario"));
                   // titulo.setText(usuariocreador);
                }

            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

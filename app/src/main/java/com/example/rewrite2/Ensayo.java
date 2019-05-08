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

public class Ensayo extends AppCompatActivity {
    private EditText titulo, intro, desa, con, ref;
    private FloatingActionButton guardar, salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensayo);

        titulo = findViewById(R.id.editTituloEnsayo);
        intro = findViewById(R.id.editIntro);
        desa = findViewById(R.id.editDesa);
        con = findViewById(R.id.editConc);
        ref = findViewById(R.id.editRefe);
        guardar = findViewById(R.id.guardarEnsayo);
        salir = findViewById(R.id.salirEnsayo);

        intro.setMovementMethod(new ScrollingMovementMethod());
        desa.setMovementMethod(new ScrollingMovementMethod());
        con.setMovementMethod(new ScrollingMovementMethod());
        ref.setMovementMethod(new ScrollingMovementMethod());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String id =intent.getStringExtra("idusuario3");
                String desarrollo, conclusion, refere, introdu, tit;

                desarrollo = desa.getText().toString();
                introdu = intro.getText().toString();
                tit = titulo.getText().toString();
                conclusion = con.getText().toString();
                refere = ref.getText().toString();

                AdminSQLiteOpenHelper soporte = new AdminSQLiteOpenHelper(Ensayo.this);
                SQLiteDatabase bd = soporte.getWritableDatabase();
                Cursor buscar = bd.rawQuery("select * from usuario where idusuario = '"+id+"'", null);
                if (buscar.moveToFirst()) {
                    Cursor validar = bd.rawQuery("select * from ensayo where idusuario2 = '"+id+"' and tituloensayo = '"+tit+"'", null);
                    if (validar.moveToFirst()){
                        Toast.makeText(Ensayo.this,"Ya tienes un ensayo con ese título.", Toast.LENGTH_SHORT).show();
                    } else {
                        SQLiteDatabase BaseDeDatos = soporte.getWritableDatabase();

                        ContentValues nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("tituloensayo", tit);
                        nuevoRegistro.put("intro", introdu);
                        nuevoRegistro.put("desarrollo", desarrollo);
                        nuevoRegistro.put("conclusion", conclusion);
                        nuevoRegistro.put("referencias", refere);
                        nuevoRegistro.put("idusuario2", id);
                        BaseDeDatos.insert("ensayo", null, nuevoRegistro);

                        Toast.makeText(Ensayo.this, "Obra guardada.", Toast.LENGTH_SHORT).show();

                        finish();
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

    }
}

package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
private EditText usuario, contra;
private Button rigstrar;
private FloatingActionButton regresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = findViewById(R.id.regisusuario);
        contra = findViewById(R.id.regiscontra);
        rigstrar = findViewById(R.id.btnaltausuario);
        regresa = findViewById(R.id.regresaRegistro);
        rigstrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre =usuario.getText().toString();
                String pass = contra.getText().toString();
                if (nombre.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Registro.this,"Hay campos vacios.", Toast.LENGTH_SHORT).show();
                } else {



                    AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(Registro.this);
                    SQLiteDatabase bd = alta.getWritableDatabase();
                    Cursor buscar = bd.rawQuery("select * from usuario where usuario = '"+nombre+"'", null);

                    if (buscar.moveToFirst()){
                        Toast.makeText(Registro.this,"Nombre de usuario no disponible.", Toast.LENGTH_SHORT).show();
                    } else {
                        SQLiteDatabase BaseDeDatos = alta.getWritableDatabase();

                        ContentValues nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("usuario", nombre);
                        nuevoRegistro.put("pass", pass);
                        nuevoRegistro.put("tipo", "escritor");
                        BaseDeDatos.insert("usuario", null, nuevoRegistro);

                     /*   nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("usuario", "Marco");
                        nuevoRegistro.put("pass", "123");
                        nuevoRegistro.put("tipo", "editor");
                        BaseDeDatos.insert("usuario", null, nuevoRegistro);

                        nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("usuario", "Hugo");
                        nuevoRegistro.put("pass", "123");
                        nuevoRegistro.put("tipo", "operador");
                        BaseDeDatos.insert("usuario", null, nuevoRegistro);

                        nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("usuario", "Pedro");
                        nuevoRegistro.put("pass", "123");
                        nuevoRegistro.put("tipo", "ingenieroE");
                        BaseDeDatos.insert("usuario", null, nuevoRegistro);


                    nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("titulo", "obraprueba");
                    nuevoRegistro.put("fecha", "12/09/2019");
                    nuevoRegistro.put("tipo", "lienzo");
                    nuevoRegistro.put("idusuario1", 1);
                    BaseDeDatos.insert("obra", null, nuevoRegistro);*/

                        BaseDeDatos.close();



                        Toast.makeText(Registro.this, "Registro exitoso.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registro.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(Registro.this, Login.class);
                startActivity(o);
                finish();
            }
        });

    }
}

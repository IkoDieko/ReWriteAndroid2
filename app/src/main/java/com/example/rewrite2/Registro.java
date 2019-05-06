package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
private EditText usuario, contra;
private Button rigstrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = findViewById(R.id.regisusuario);
        contra = findViewById(R.id.regiscontra);
        rigstrar = findViewById(R.id.btnaltausuario);
        rigstrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre =usuario.getText().toString();
                String pass = contra.getText().toString();
                if (nombre.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Registro.this,"Hay campos vacios.", Toast.LENGTH_SHORT).show();
                } else {
                    AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(Registro.this, "usuario", null, 1);
                    SQLiteDatabase BaseDeDatos = alta.getWritableDatabase();

                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("usuario", nombre);
                    nuevoRegistro.put("pass", pass);
                    BaseDeDatos.insert("usuario", null, nuevoRegistro);
                    BaseDeDatos.close();

                    Toast.makeText(Registro.this,"Registro exitoso.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Registro.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}

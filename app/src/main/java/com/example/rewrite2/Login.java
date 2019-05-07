package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText usu, contra;
    private Button inicia, registra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usu = findViewById(R.id.editUsu);
        contra = findViewById(R.id.editContra);
        inicia = findViewById(R.id.btnIniciar);
        registra = findViewById(R.id.btn_registrar);


        inicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usu.getText().toString();
                String pass = contra.getText().toString();

                AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(Login.this, "usuario", null, 1);
                SQLiteDatabase bd = alta.getWritableDatabase();
                Cursor buscar = bd.rawQuery("select * from usuario where usuario = '"+usuario+"' and pass ='"+pass+"'", null);

                String tipo = "";

                if (buscar.moveToFirst()) {
                    tipo = buscar.getString(buscar.getColumnIndex("tipo"));
                    System.out.println(tipo);
                    Intent i = new Intent(Login.this, Main.class);;
                    if(tipo.equals("escritor")){
                        i = new Intent(Login.this, Main.class);
                    }else if(tipo.equals("ingenieroE")){
                        i = new Intent(Login.this, InicioEventos.class);
                    }else if(tipo.equals("operador")){
                        i = new Intent(Login.this, InicioEventos.class);
                    }else if(tipo.equals("gerenteE")){
                        i = new Intent(Login.this, InicioEventos.class);
                    }else if(tipo.equals("gerenteM")){
                        i = new Intent(Login.this, Main.class);
                    }else if(tipo.equals("programador")){
                        i = new Intent(Login.this, Main.class);
                    }
                    String id;
                    id = buscar.getString(buscar.getColumnIndex("idusuario"));

                    i.putExtra("idusuario", id);
                    i.putExtra("tipo", tipo);
                    startActivity(i);
                    finish();;

                } else {
                    Toast.makeText(Login.this,"Usuario y/o contraseña no coinciden.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registro.class);
                startActivity(i);
                finish();
            }
        });
    }
}

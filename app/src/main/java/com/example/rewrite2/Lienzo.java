package com.example.rewrite2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;

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
                String usuariocreador =intent.getStringExtra("usuariocreador");
                titulo.setText(usuariocreador);
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

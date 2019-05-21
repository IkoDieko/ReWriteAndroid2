package com.example.rewrite2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InicioEventos extends AppCompatActivity {

    private Button btnNuevo, btnVer;
    private TextView txtTipoUs, txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_eventos);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnNuevo = (Button) findViewById(R.id.btnAlta);
        btnVer = (Button) findViewById(R.id.btnConsulta);
        txtTipoUs = (TextView) findViewById(R.id.labelTipoUsuario);
        txtUsuario = (TextView) findViewById(R.id.labelUsuario);

        Intent in = getIntent();
        String us = in.getStringExtra("usuario");
        String tipo = in.getStringExtra("tipo");

        txtUsuario.setText(us);

        if(tipo.equals("ingenieroE")){
            tipo = "Ingeniero de soporte:";
        }else if(tipo.equals("operador")){
            tipo = "Operador:";
        }else if(tipo.equals("gerenteE")){
            tipo = "Gerente de eventos:";
        }
        txtTipoUs.setText(tipo);
        txtUsuario.setText(us);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String idusuario = in.getStringExtra("idusuario");
                Intent i = new Intent(InicioEventos.this, AltaReporte.class);
                i.putExtra("idusuario", idusuario);
                startActivity(i);
            }
        });
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String tipo = in.getStringExtra("tipo");
                String idusuario = in.getStringExtra("idusuario");
                Intent i = new Intent(InicioEventos.this, VerReportesE.class);
                i.putExtra("tipo2", tipo);
                i.putExtra("idusuario", idusuario);
                startActivity(i);
            }
        });


    }
}

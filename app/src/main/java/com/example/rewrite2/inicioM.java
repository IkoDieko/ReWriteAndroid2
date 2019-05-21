package com.example.rewrite2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class inicioM extends AppCompatActivity {
    private Button altaN, altaRE, consulta;
    private TextView tipo, usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_m);
        FloatingActionButton fab = findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        altaN = findViewById(R.id.btNM);
        altaRE = findViewById(R.id.AltadeEventos);
        consulta = findViewById(R.id.btnConsultaM);
        tipo =findViewById(R.id.tipousuM);
        usu = findViewById(R.id.NombreUsuM);

        Intent in = getIntent();
        String us = in.getStringExtra("usuario");
        String tipo1 = in.getStringExtra("tipo");
        String idusu = in.getStringExtra("idusuario");

        usu.setText(us);
        String tipo2 = "";

        if(tipo1.equals("ingenieroM")){
            tipo2 = "Ingeniero de Mantenimiento:";
        }else if(tipo1.equals("programador")){
            tipo2 = "Programador:";
        }else if(tipo1.equals("gerenteM")){
            tipo2 = "Gerente de Mantenimiento:";
        }
        tipo.setText(tipo2);

        altaN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String idusuario = in.getStringExtra("idusuario");
                Intent i = new Intent(inicioM.this, AltaReporteMCero.class);
                i.putExtra("idusuario", idusuario);
                startActivity(i);
            }
        });
        altaRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String tipo = in.getStringExtra("tipo");
                String idusuario = in.getStringExtra("idusuario");
                Intent i = new Intent(inicioM.this, VerReportesM.class);
                i.putExtra("tipo2", tipo);
                i.putExtra("idusuario", idusuario);
                startActivity(i);
            }
        });




    }
}

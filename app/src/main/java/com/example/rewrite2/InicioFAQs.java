package com.example.rewrite2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class InicioFAQs extends AppCompatActivity {

    private Button btnAltas, btnRevisa, btnAltaCero;
    private String usID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_faqs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent miInt2 = getIntent();
        usID = miInt2.getStringExtra("idusuario");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAltas = (Button) findViewById(R.id.btnAltaF);
        btnRevisa = (Button) findViewById(R.id.btnRevisarF);
        btnAltaCero = (Button) findViewById(R.id.btnAltaCero);

        btnAltas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioFAQs.this, AltasFAQ.class);
                i.putExtra("idusuario",usID);
                startActivity(i);
            }
        });

        btnRevisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioFAQs.this, RevisaFAQ.class);
                i.putExtra("idusuario",usID);
                startActivity(i);
            }
        });
        btnAltaCero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioFAQs.this, AltaFAQCero.class);
                i.putExtra("idusuario",usID);
                startActivity(i);
            }
        });

    }

}

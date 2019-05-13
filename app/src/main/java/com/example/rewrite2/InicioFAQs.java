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

    private Button btnAltas, btnPublica, btnRevisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_faqs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAltas = (Button) findViewById(R.id.btnAltaF);
        btnPublica = (Button) findViewById(R.id.btnPublicaF);
        btnRevisa = (Button) findViewById(R.id.btnRevisarF);

        btnAltas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioFAQs.this, AltasFAQ.class);
                startActivity(i);
            }
        });
        btnPublica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioFAQs.this, PublicaFAQ.class);
                startActivity(i);
            }
        });
        btnRevisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioFAQs.this, RevisaFAQ.class);
                startActivity(i);
            }
        });

    }

}

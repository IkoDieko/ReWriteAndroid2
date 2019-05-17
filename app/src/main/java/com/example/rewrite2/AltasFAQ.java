package com.example.rewrite2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AltasFAQ extends AppCompatActivity {
    private Button btnVerRepF1, btnVerRepF2,btnVerRepF3,btnVerRepF4,btnVerRepF5, btnVerRepFB;
    private Button btnBuscarF;
    private TextView txtFolioF1,txtFolioF2,txtFolioF3,txtFolioF4,txtFolioF5,txtFolioFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas_faq);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Casteando botones para ver Reporte con detalles en dARaLTAFAQ.class
        btnVerRepF1 = (Button) findViewById(R.id.btnVerRF1);
        btnVerRepF2 = (Button) findViewById(R.id.btnVerRF2);
        btnVerRepF3 = (Button) findViewById(R.id.btnVerRF3);
        btnVerRepF4 = (Button) findViewById(R.id.btnVerRF4);
        btnVerRepF5 = (Button) findViewById(R.id.btnVerRF5);
        btnVerRepFB = (Button) findViewById(R.id.btnVerRBuscaF);
        txtFolioF1 = (TextView) findViewById(R.id.labelFolioF1);
        txtFolioF2 = (TextView) findViewById(R.id.labelFolioF2);
        txtFolioF3 = (TextView) findViewById(R.id.labelFolioF3);
        txtFolioF4 = (TextView) findViewById(R.id.labelFolioF4);
        txtFolioF5 = (TextView) findViewById(R.id.labelFolioF5);
        txtFolioFB = (TextView) findViewById(R.id.labelFolioBuscaF);

        btnVerRepF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(AltasFAQ.this, DarALtaFAQ.class);
                miIntent.putExtra("Folio", txtFolioF1.getText());
                startActivity(miIntent);
            }
        });
        btnVerRepF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(AltasFAQ.this, DarALtaFAQ.class);
                miIntent.putExtra("Folio", txtFolioF2.getText());
                startActivity(miIntent);
            }
        });
        btnVerRepF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(AltasFAQ.this, DarALtaFAQ.class);
                miIntent.putExtra("Folio", txtFolioF3.getText());
                startActivity(miIntent);
            }
        });
        btnVerRepF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(AltasFAQ.this, DarALtaFAQ.class);
                miIntent.putExtra("Folio", txtFolioF4.getText());
                startActivity(miIntent);
            }
        });
        btnVerRepF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(AltasFAQ.this, DarALtaFAQ.class);
                miIntent.putExtra("Folio", txtFolioF5.getText());
                startActivity(miIntent);
            }
        });
        btnVerRepFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(AltasFAQ.this, DarALtaFAQ.class);
                miIntent.putExtra("Folio", txtFolioFB.getText());
                startActivity(miIntent);
            }
        });


    }

}

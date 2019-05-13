package com.example.rewrite2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerReportesE extends AppCompatActivity {
    private Button btnVerRep1, btnVerRep2,btnVerRep3,btnVerRep4,btnVerRep5, btnVerRepB;
    private Button btnBuscar;
    private TextView txtFolio1,txtFolio2,txtFolio3,txtFolio4,txtFolio5,txtFolioB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reportes_e);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Casteando botones para ver Reporte con detalles en ReporteEvento.class
        btnVerRep1 = (Button) findViewById(R.id.btnVerR1);
        btnVerRep2 = (Button) findViewById(R.id.btnVerRF2);
        btnVerRep3 = (Button) findViewById(R.id.btnVerR3);
        btnVerRep4 = (Button) findViewById(R.id.btnVerR4);
        btnVerRep5 = (Button) findViewById(R.id.btnVerR5);
        btnVerRepB = (Button) findViewById(R.id.btnVerRBusca);
        txtFolio1 = (TextView) findViewById(R.id.labelFolio1);
        txtFolio2 = (TextView) findViewById(R.id.labelFolio2);
        txtFolio3 = (TextView) findViewById(R.id.labelFolio3);
        txtFolio4 = (TextView) findViewById(R.id.labelFolioF4);
        txtFolio5 = (TextView) findViewById(R.id.labelFolio5);
        txtFolioB = (TextView) findViewById(R.id.labelFolioBusca);

        btnVerRep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(VerReportesE.this,ReporteEvento.class);
                miIntent.putExtra("Folio",txtFolio1.getText());
                startActivity(miIntent);
            }
        });
        btnVerRep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(VerReportesE.this,ReporteEvento.class);
                miIntent.putExtra("Folio",txtFolio2.getText());
                startActivity(miIntent);
            }
        });
        btnVerRep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(VerReportesE.this,ReporteEvento.class);
                miIntent.putExtra("Folio",txtFolio3.getText());
                startActivity(miIntent);
            }
        });
        btnVerRep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(VerReportesE.this,ReporteEvento.class);
                miIntent.putExtra("Folio",txtFolio4.getText());
                startActivity(miIntent);
            }
        });
        btnVerRep5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(VerReportesE.this,ReporteEvento.class);
                miIntent.putExtra("Folio",txtFolio5.getText());
                startActivity(miIntent);
            }
        });
        btnVerRepB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(VerReportesE.this,ReporteEvento.class);
                miIntent.putExtra("Folio",txtFolioB.getText());
                startActivity(miIntent);
            }
        });

    }

}

package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AltaReporteMCero extends AppCompatActivity {
    private Button enviar;
    private EditText etiqueta, problema, fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reporte_mcero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        enviar = findViewById(R.id.btnEnviarM);
        etiqueta = findViewById(R.id.editEtiquetaM);
        fecha = findViewById(R.id.fechaM);
        problema = findViewById(R.id.problemaM);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et = etiqueta.getText().toString();
                String pro = problema.getText().toString();
                String fe = fecha.getText().toString();
                Intent in = getIntent();
                String id = in.getStringExtra("idusuario");

                if (et.isEmpty() || pro.isEmpty() || fe.isEmpty()){
                    Toast.makeText(AltaReporteMCero.this,"Faltan campos por rellenar.", Toast.LENGTH_SHORT).show();
                } else{
                    AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(AltaReporteMCero.this);
                    SQLiteDatabase bd = alta.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("idlevanta1", id);
                    cv.put("fechaLevanta", fe);
                    cv.put("etiqueta", et);
                    cv.put("estado", "Creado");
                    cv.put("problema", pro);
                    bd.insert("reporteM", null, cv);
                    Toast.makeText(AltaReporteMCero.this,"Reporte creado.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

}

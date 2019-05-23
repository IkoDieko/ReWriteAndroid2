package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DarALtaFAQ extends AppCompatActivity {

    TextView txtEtiQ,txtProb,txtSol;
    EditText editEti,editPre,editRes;
    Button btnGuardar;
    AdminSQLiteOpenHelper buscar;
    SQLiteDatabase bd;
    String folio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_alta_faq);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent miInt1 = getIntent();

        txtEtiQ = (TextView) findViewById(R.id.labelEtiR);
        txtProb = (TextView) findViewById(R.id.labelProblema);
        txtSol = (TextView) findViewById(R.id.labelSolucion);
        editEti = (EditText) findViewById(R.id.editEtiQ);
        editPre  = (EditText) findViewById(R.id.editPreQ);
        editRes  = (EditText) findViewById(R.id.editResQ);
        btnGuardar = (Button) findViewById(R.id.btnGuardaQ);

        folio = miInt1.getStringExtra("idFolio");

        buscar = new AdminSQLiteOpenHelper(DarALtaFAQ.this);
        bd = buscar.getWritableDatabase();

        String problema1 = "";
        String etiqueta1 = "";
        String soluc = "";

        Cursor validar = bd.rawQuery("select * from reporteE where idreporteE = "+folio, null);
        if (validar.moveToFirst()){
            problema1 = validar.getString(validar.getColumnIndex("problema"));
            etiqueta1 = validar.getString(validar.getColumnIndex("etiqueta"));
            soluc = validar.getString(validar.getColumnIndex("solucion"));
        }

        txtEtiQ.setText(etiqueta1);
        txtProb.setText(problema1);
        txtSol.setText(soluc);

        editPre.setText(problema1);
        editRes.setText(soluc);
        editEti.setText(etiqueta1);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editRes.getText().toString().equals("")&&!editPre.getText().toString().equals("")
                        &&!editEti.getText().toString().equals("")){
                    AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(DarALtaFAQ.this);
                    bd = alta.getWritableDatabase();

                    String eti = editEti.getText().toString();
                    String pre = editPre.getText().toString();
                    String res = editRes.getText().toString();

                    Intent miInt = getIntent();
                    String us = miInt.getStringExtra("idusuario");

                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("pregunta", pre);
                    nuevoRegistro.put("solucion", res);
                    nuevoRegistro.put("etiqueta", eti);
                    nuevoRegistro.put("idlevanta2", us);
                    nuevoRegistro.put("idrepEvento1", folio);
                    bd.insert("FAQs", null, nuevoRegistro);
                    bd.close();

                    Toast.makeText(DarALtaFAQ.this, "¡FAQ añadida!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DarALtaFAQ.this, "¡Falta llenar algún campo!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

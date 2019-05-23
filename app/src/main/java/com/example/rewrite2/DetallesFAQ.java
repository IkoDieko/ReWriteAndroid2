package com.example.rewrite2;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class DetallesFAQ extends AppCompatActivity {

    EditText editEtiF, editPreF, editSolF, editRepE;
    Button btnGuardaF, btnEliminaF;
    TextView txtNumFAQ;
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase bd;
    String idFAQ;
    Dialog miPop;
    Button btnAcepta, btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_faq);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin = new AdminSQLiteOpenHelper(DetallesFAQ.this);
        bd = admin.getWritableDatabase();

        miPop = new Dialog(this);

        editEtiF = (EditText) findViewById(R.id.editEtiF);
        editPreF = (EditText) findViewById(R.id.editPreF);
        editSolF = (EditText) findViewById(R.id.editResF);
        editRepE = (EditText) findViewById(R.id.editRepF);

        btnEliminaF = (Button) findViewById(R.id.btnEliminaFR);
        btnGuardaF = (Button) findViewById(R.id.btnGuardaFR);

        txtNumFAQ = (TextView) findViewById(R.id.txtNumFAQ);

        Intent miInt = getIntent();
        String pregunta = miInt.getStringExtra("pregunta");
        String respuesta = miInt.getStringExtra("FSol");
        String reporte = "Problema: "+ miInt.getStringExtra("EProb") +
                "\nSolución: " + miInt.getStringExtra("ESol") ;
        String etiqueta = miInt.getStringExtra("etiqueta");
        idFAQ = miInt.getStringExtra("idFAQ");

        editPreF.setText(pregunta);
        editSolF.setText(respuesta);
        editEtiF.setText(etiqueta);
        txtNumFAQ.setText("# "+idFAQ);

        if(etiqueta.equals("Sin evento")){
            editRepE.setText(" - ");
        } else {
            editRepE.setText(reporte);
        }

        btnEliminaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verPopUp(v);
            }
        });

        btnGuardaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("etiqueta",editEtiF.getText().toString());
                cv.put("pregunta",editPreF.getText().toString());
                cv.put("solucion",editSolF.getText().toString());
                bd.update("FAQs",cv,"idFAQ ="+idFAQ,null);

                Toast.makeText(DetallesFAQ.this, "Se actualizó la FAQ", Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void verPopUp(View v){


        miPop.setContentView(R.layout.popup);

        btnAcepta = (Button) miPop.findViewById(R.id.btnAcepta);
        btnCancela = (Button) miPop.findViewById(R.id.btnCancela);

        btnAcepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.execSQL("DELETE FROM FAQs WHERE idFAQ="+idFAQ);
                Toast.makeText(DetallesFAQ.this, "Se eliminó la FAQ", Toast.LENGTH_SHORT).show();
                miPop.dismiss();
                finish();
            }
        });

        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miPop.dismiss();
            }
        });
        miPop.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        miPop.show();
    }

}

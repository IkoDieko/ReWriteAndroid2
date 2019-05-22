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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RevisaFAQ extends AppCompatActivity {

    Button btnBusca, btnVerFQ1, btnVerFQ2,btnVerFQ3, btnVerFQ4;
    TextView txtEtiFQ1,txtEtiFQ2,txtEtiFQ3,txtEtiFQ4;
    EditText editBusca;
    RadioButton rBtnEti, rBtnNum;
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase bd;
    String us;
    Intent miInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisa_faq);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        admin = new AdminSQLiteOpenHelper(RevisaFAQ.this);
        bd = admin.getWritableDatabase();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        miInt = getIntent();
        us = miInt.getStringExtra("idusuario");

        btnBusca = (Button) findViewById(R.id.btnBuscaFQ);
        /*btnVerFQ1 = (Button)findViewById(R.id.btnFQ1);
        btnVerFQ2 = (Button)findViewById(R.id.btnFQ2);
        btnVerFQ3 = (Button)findViewById(R.id.btnFQ3);
        btnVerFQ4 = (Button)findViewById(R.id.btnFQ4);

        txtEtiFQ1 = (TextView) findViewById(R.id.txtEtiFQ1);
        txtEtiFQ2 = (TextView) findViewById(R.id.txtEtiFQ2);
        txtEtiFQ3 = (TextView) findViewById(R.id.txtEtiFQ3);
        txtEtiFQ4 = (TextView) findViewById(R.id.txtEtiFQ4);*/

        editBusca = (EditText) findViewById(R.id.editBuscaFQ);
        rBtnEti = (RadioButton) findViewById(R.id.rbtnEti);
        rBtnNum = (RadioButton) findViewById(R.id.rbtnNum);

        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor buscar = null;
                String criterio = "";
                String buscando = editBusca.getText().toString();

                buscar = bd.rawQuery("select * from FAQs ", null);
                while(buscar.moveToNext()){
                    System.out.println("Encuentra");
                    System.out.println("FAQ:" +buscar.getString(buscar.getColumnIndex("idFAQ"))+ ".- "+
                            buscar.getString(buscar.getColumnIndex("pregunta")));
                }

                if(rBtnEti.isChecked()||rBtnNum.isChecked()){
                    if(rBtnEti.isChecked()){
                        System.out.println("Busca por Etiqueta: "+buscando);
                        buscar = bd.rawQuery("select idFAQ,pregunta,problema,FAQs.etiqueta AS eti," +
                                "FAQs.solucion AS FSol, FAQs.solucion AS FSol, problema, reporteE.solucion AS ESol, idreporteE," +
                                " reporteE.etiqueta AS etiE" +
                                " from FAQs inner join reporteE on reporteE.idreporteE = FAQs.idrepEvento1" +
                                " where FAQs.etiqueta ='"+buscando+"'", null);
                    }else if(rBtnNum.isChecked()){
                        System.out.println("Busca por Numero");
                        buscar = bd.rawQuery("select idFAQ,pregunta,problema,FAQs.etiqueta AS eti," +
                                "FAQs.solucion AS FSol, FAQs.solucion AS FSol, problema, reporteE.solucion AS ESol, idreporteE," +
                                " reporteE.etiqueta AS etiE" +
                                " from FAQs inner join reporteE on reporteE.idreporteE = FAQs.idrepEvento1" +
                                " where FAQs.idFAQ ="+buscando, null);
                    }
                    if (buscar.moveToNext()){
                        System.out.println("FAQ encontrado: "+buscar.getString(buscar.getColumnIndex("pregunta")));
                        Intent i = new Intent(RevisaFAQ.this, DetallesFAQ.class);
                        i.putExtra("idFAQ",buscar.getString(buscar.getColumnIndex("idFAQ")));
                        i.putExtra("etiqueta",buscar.getString(buscar.getColumnIndex("eti")));
                        i.putExtra("pregunta",buscar.getString(buscar.getColumnIndex("pregunta")));
                        i.putExtra("FSol",buscar.getString(buscar.getColumnIndex("FSol")));
                        i.putExtra("EProb",buscar.getString(buscar.getColumnIndex("problema")));
                        i.putExtra("ESol",buscar.getString(buscar.getColumnIndex("ESol")));
                        i.putExtra("EEti",buscar.getString(buscar.getColumnIndex("etiE")));
                        startActivity(i);
                    } else {
                        Toast.makeText(RevisaFAQ.this, "No se encontró el FAQ", Toast.LENGTH_SHORT).show();
                    }

                }  else {
                    Toast.makeText(RevisaFAQ.this, "¡Selecciona un criterio de búsqueda!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}

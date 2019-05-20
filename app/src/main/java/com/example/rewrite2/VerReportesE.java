package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerReportesE extends AppCompatActivity {
private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reportes_e);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.listReporteEventos);
        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(VerReportesE.this);
        SQLiteDatabase bd = juego.getWritableDatabase();

        ArrayList<String> rankingL = new ArrayList<>();
        Cursor filaL = bd.rawQuery("select * from reporteE", null);
        if(filaL.moveToFirst()){
            do{
                rankingL.add(filaL.getString(filaL.getColumnIndex("idreporteE")) + "        " +
                             filaL.getString(filaL.getColumnIndex("fechaLevanta")) + "      " +
                             filaL.getString(filaL.getColumnIndex("estado")) + "        " +
                             filaL.getString(filaL.getColumnIndex("etiqueta")));
            }while(filaL.moveToNext());
        }
        ArrayAdapter<String> adapterL = new ArrayAdapter<String>(VerReportesE.this, android.R.layout.simple_list_item_1, rankingL){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
               textView.setTextSize(17);
                return view;
            }
        };
        lista.setAdapter(adapterL);
        lista.setClickable(true);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = getIntent();
                String tipo = in.getStringExtra("tipo2");
                String idusuario = in.getStringExtra("idusuario");
                String item = (String) lista.getItemAtPosition(position);
                String foo = item;
                String[] bar = foo.split("(?=\\s)");
                //Toast.makeText(view.getContext(),"Posición: " + bar[0], Toast.LENGTH_SHORT).show();
                Intent L = new Intent(VerReportesE. this, ReporteEvento.class);
                L.putExtra("idFolio", bar[0]);
                L.putExtra("tipo3", tipo);
                L.putExtra("idusuario", idusuario);
                startActivity(L);


            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Casteando botones para ver Reporte con detalles en ReporteEvento.class


    }


}

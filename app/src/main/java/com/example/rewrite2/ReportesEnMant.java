package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportesEnMant extends AppCompatActivity {
    private ListView lista;
    private FloatingActionButton regresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_en_mant);
        lista = findViewById(R.id.listaRepEnMant);
        regresa = findViewById(R.id.regresaReportesMant);

        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(ReportesEnMant.this);
        SQLiteDatabase bd = juego.getWritableDatabase();

        ArrayList<String> rankingL = new ArrayList<>();
        Cursor filaL = bd.rawQuery("select * from reporteE where estado = 'En Mantenimiento'", null);
        if(filaL.moveToFirst()){
            do{
                rankingL.add(filaL.getString(filaL.getColumnIndex("idreporteE")) + "               " +
                        filaL.getString(filaL.getColumnIndex("estado")) + "              " +
                        filaL.getString(filaL.getColumnIndex("etiqueta")));
            }while(filaL.moveToNext());
        }
        ArrayAdapter<String> adapterL = new ArrayAdapter<String>(ReportesEnMant.this, android.R.layout.simple_list_item_1, rankingL){
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

                AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(ReportesEnMant.this);
                SQLiteDatabase bd = juego.getWritableDatabase();
                Cursor verifica = bd.rawQuery("select * from reporteE where idreporteE = '"+bar[0]+"'", null);
                if (verifica.moveToFirst()){
                    String problema, etiqueta, fecha;
                    problema = verifica.getString(verifica.getColumnIndex("problema"));
                    etiqueta = verifica.getString(verifica.getColumnIndex("etiqueta"));
                    fecha = verifica.getString(verifica.getColumnIndex("fechaLevanta"));

                    ContentValues cv = new ContentValues();
                    cv.put("idlevanta1", idusuario);
                    cv.put("fechaLevanta", fecha);
                    cv.put("etiqueta", etiqueta);
                    cv.put("estado", "Creado");
                    cv.put("problema", problema);
                    bd.insert("reporteM", null, cv);
                    Toast.makeText(ReportesEnMant.this,"Reporte creado.", Toast.LENGTH_SHORT).show();
                    finish();
                }



            }
        });

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

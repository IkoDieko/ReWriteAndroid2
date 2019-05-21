package com.example.rewrite2;

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

public class VerReportesM extends AppCompatActivity {
    private ListView lista;
    private FloatingActionButton regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reportes_m);

        regresar = findViewById(R.id.regresarVerM);
        lista = findViewById(R.id.listReportesM);

        Intent in = getIntent();
        String tipo = in.getStringExtra("tipo2");
        String idusuario = in.getStringExtra("idusuario");


        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(VerReportesM.this);
        SQLiteDatabase bd = juego.getWritableDatabase();

        ArrayList<String> rankingL = new ArrayList<>();

        if (tipo.equals("gerenteM")) {
            Cursor filaL = bd.rawQuery("select * from reporteM", null);
            if (filaL.moveToFirst()) {
                do {
                    rankingL.add(filaL.getString(filaL.getColumnIndex("idreporteM")) + "                         " +
                            filaL.getString(filaL.getColumnIndex("estado")) + "                      " +
                            filaL.getString(filaL.getColumnIndex("etiqueta")));
                } while (filaL.moveToNext());
            }
            ArrayAdapter<String> adapterL = new ArrayAdapter<String>(VerReportesM.this, android.R.layout.simple_list_item_1, rankingL) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setTextSize(17);
                    return view;
                }
            };
            lista.setAdapter(adapterL);
        }
        else {
            Cursor filaL = bd.rawQuery("select * from reporteM where idasignado1 = '"+idusuario+"'", null);
            if (filaL.moveToFirst()) {
                do {
                    rankingL.add(filaL.getString(filaL.getColumnIndex("idreporteM")) + "            " +
                            filaL.getString(filaL.getColumnIndex("estado")) + "         " +
                            filaL.getString(filaL.getColumnIndex("etiqueta")));
                } while (filaL.moveToNext());
            }
            ArrayAdapter<String> adapterL = new ArrayAdapter<String>(VerReportesM.this, android.R.layout.simple_list_item_1, rankingL) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setTextSize(17);
                    return view;
                }
            };
            lista.setAdapter(adapterL);
        }
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
                Intent L = new Intent(VerReportesM. this, ReporteM.class);
                L.putExtra("idFolio", bar[0]);
                L.putExtra("tipo3", tipo);
                L.putExtra("idusuario", idusuario);
                startActivity(L);


            }
        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

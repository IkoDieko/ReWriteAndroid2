package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AltasFAQ extends AppCompatActivity {
    private Button btnVerRepF1, btnVerRepF2,btnVerRepF3,btnVerRepF4,btnVerRepF5, btnVerRepFB;
    private Button btnBuscarF;
    private TextView txtFolioF1,txtFolioF2,txtFolioF3,txtFolioF4,txtFolioF5,txtFolioFB;
    private ListView lista;

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


        lista = findViewById(R.id.listReporteEventosF);
        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(AltasFAQ.this);
        SQLiteDatabase bd = juego.getWritableDatabase();

        ArrayList<String> rankingL = new ArrayList<>();
        Cursor filaL = bd.rawQuery("select * from reporteE where estado = 'Cerrado'", null);
        if(filaL.moveToFirst()){
            do{
                rankingL.add(filaL.getString(filaL.getColumnIndex("idreporteE")) + "        " +
                        filaL.getString(filaL.getColumnIndex("fechaLevanta")) + "      " +
                        filaL.getString(filaL.getColumnIndex("estado")) + "        " +
                        filaL.getString(filaL.getColumnIndex("etiqueta")));
            }while(filaL.moveToNext());
        }
        ArrayAdapter<String> adapterL = new ArrayAdapter<String>(AltasFAQ.this, android.R.layout.simple_list_item_1, rankingL){
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
                Intent L = new Intent(AltasFAQ. this, DarALtaFAQ.class);
                L.putExtra("idFolio", bar[0]);
                L.putExtra("tipo3", tipo);
                L.putExtra("idusuario", idusuario);
                startActivity(L);


            }
        });


    }

}

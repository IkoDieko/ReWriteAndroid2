package com.example.rewrite2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FAQsU extends AppCompatActivity {

    private ListView lista;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs_u);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lista = findViewById(R.id.listFAQs1);

        admin = new AdminSQLiteOpenHelper(FAQsU.this);
        bd = admin.getWritableDatabase();

        ArrayList<String> array = new ArrayList<>();
        Cursor filaL = bd.rawQuery("select * from FAQs", null);
        if(filaL.moveToFirst()){
            do{
                array.add(filaL.getString(filaL.getColumnIndex("pregunta")));
                array.add(filaL.getString(filaL.getColumnIndex("solucion")));
            }while(filaL.moveToNext());
        }
        ArrayAdapter<String> adapterL = new ArrayAdapter<String>(FAQsU.this, android.R.layout.simple_list_item_1, array){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(18);
                return view;
            }
        };
        lista.setAdapter(adapterL);
    }

}

package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentoMisObras extends Fragment {
    private ListView listaL, listaE;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragmento_misobras, container, false);
        listaL = view.findViewById(R.id.listLienzo);
        listaE = view.findViewById(R.id.listEnsayo);
        String id;
        id = getActivity().getIntent().getStringExtra("idusuario");
        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(view.getContext());
        SQLiteDatabase bd = juego.getWritableDatabase();

       ArrayList<String> rankingL = new ArrayList<>();
        Cursor filaL = bd.rawQuery("select * from lienzo where idusuario1 = '"+id+"'", null);
       if(filaL.moveToFirst()){
            do{
                rankingL.add(filaL.getString(filaL.getColumnIndex("titulolienzo")));
            }while(filaL.moveToNext());
        }
        ArrayAdapter<String> adapterL = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, rankingL){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(24);
                return view;
            }
        };
        listaL.setAdapter(adapterL);
        listaL.setClickable(true);
        listaL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listaL.getItemAtPosition(position);
                String idusuario;
                idusuario = getActivity().getIntent().getStringExtra("idusuario");
                //Toast.makeText(view.getContext(),"Posición: " + item,Toast.LENGTH_SHORT).show();
                Intent L = new Intent(view.getContext(), EditLienzo.class);
                L.putExtra("idusuario3", idusuario);
                L.putExtra("TituloL", item);
                startActivity(L);

            }
        });


        ArrayList<String> rankingE = new ArrayList<>();
        Cursor filaE = bd.rawQuery("select * from ensayo where idusuario2 = '"+id+"'", null);
        if(filaE.moveToFirst()){
            do{
                rankingE.add(filaE.getString(filaE.getColumnIndex("tituloensayo")));
            }while(filaE.moveToNext());
        }
        bd.close();
        ArrayAdapter<String> adapterE = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, rankingE){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(24);
                return view;
            }
        };
        listaE.setAdapter(adapterE);
        listaE.setClickable(true);
        listaE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        return view;

    }
}

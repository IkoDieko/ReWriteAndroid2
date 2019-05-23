package com.example.rewrite2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FAQs extends Fragment {
    private View view;
    private ListView lista;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    public FAQs() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_faqs, container, false);
        lista = view.findViewById(R.id.listFAQs);

        admin = new AdminSQLiteOpenHelper(view.getContext());
        bd = admin.getWritableDatabase();

        ArrayList<String> array = new ArrayList<>();
        Cursor filaL = bd.rawQuery("select * from FAQs", null);
        if(filaL.moveToFirst()){
            do{
                array.add(filaL.getString(filaL.getColumnIndex("pregunta")));
                array.add(filaL.getString(filaL.getColumnIndex("solucion")));
            }while(filaL.moveToNext());
        }
        ArrayAdapter<String> adapterL = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, array){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(18);
                return view;
            }
        };
        lista.setAdapter(adapterL);

        return view;
    }

}

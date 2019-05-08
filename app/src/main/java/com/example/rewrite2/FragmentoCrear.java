package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FragmentoCrear extends Fragment {
    private FloatingActionButton lienzo, ensayo;
    private View view;
    private ListView lista;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragmento_crear, container, false);

        lienzo = view.findViewById(R.id.crearLienzo);
        ensayo = view.findViewById(R.id.crearEnsayo);



        lienzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (view.getContext(), Lienzo.class );
               String id =  getActivity().getIntent().getStringExtra("idusuario");
                o.putExtra("idusuario2", id);
                startActivity(o);
            }
        });

        ensayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (view.getContext(), Ensayo.class );
                String id =  getActivity().getIntent().getStringExtra("idusuario");
                o.putExtra("idusuario3", id);
                startActivity(o);
            }
        });

        return view;

    }


}

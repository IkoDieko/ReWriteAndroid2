package com.example.rewrite2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentoCrear extends Fragment {
    private TextView correoActual, contraActual;
    private EditText contraNueva, correoNuevo;
    private Button guardar;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_crear, container, false);

        correoActual =(TextView) view.findViewById(R.id.usuarioActual);
        contraActual = (TextView) view.findViewById(R.id.contraActual);
        correoNuevo = (EditText) view.findViewById(R.id.editnuevocorreo);
        contraNueva =(EditText) view.findViewById(R.id.editcontranueva);
        guardar = (Button) view.findViewById(R.id.btnGuardarCambios);

        //String correoA = getActivity().getIntent().getStringExtra("correoheader");


        return view;

    }

    public void setDatos(){
        correoActual.setText("aasdsa");
        contraActual.setText("aaasdada");
    }

}

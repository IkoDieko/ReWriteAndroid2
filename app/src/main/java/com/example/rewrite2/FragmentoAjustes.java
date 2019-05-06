package com.example.rewrite2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentoAjustes extends Fragment {
    private TextView correoActual, contraActual;
    private EditText contraNueva, correoNuevo;
    private Button guardar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_ajustes, container, false);

        correoActual =(TextView) view.findViewById(R.id.usuarioActual);
        contraActual = (TextView) view.findViewById(R.id.contraActual);
        correoNuevo = (EditText) view.findViewById(R.id.editnuevocorreo);
        contraNueva =(EditText) view.findViewById(R.id.editcontranueva);
        guardar = (Button) view.findViewById(R.id.btnGuardarCambios);

        AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(view.getContext(), "usuario", null, 1);
        SQLiteDatabase bd = alta.getWritableDatabase();

        String contraA;
        String correoA = getActivity().getIntent().getStringExtra("correoheader");
        Cursor buscar = bd.rawQuery("select * from usuario where usuario = '"+correoA+"'", null);
        if (buscar.moveToFirst()){
            contraA = buscar.getString(buscar.getColumnIndex("pass"));
            contraActual.setText(contraA);
        }
        correoActual.setText(correoA);


        return view;
    }
}

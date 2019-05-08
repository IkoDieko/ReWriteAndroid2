package com.example.rewrite2;

import android.content.ContentValues;
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
import android.widget.Toast;

public class FragmentoAjustes extends Fragment {
    private TextView correoActual, contraActual;
    private EditText contraNueva, correoNuevo;
    private Button guardar;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_ajustes, container, false);

        correoActual =(TextView) view.findViewById(R.id.usuarioActual);
        contraActual = (TextView) view.findViewById(R.id.contraActual);
        correoNuevo = (EditText) view.findViewById(R.id.editnuevocorreo);
        contraNueva =(EditText) view.findViewById(R.id.editcontranueva);
        guardar = (Button) view.findViewById(R.id.btnGuardarCambios);

        AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(view.getContext());
        SQLiteDatabase bd = alta.getWritableDatabase();

        String contraA;
        String correoA;
        String id;
        id = getActivity().getIntent().getStringExtra("idusuario");
        Cursor buscar = bd.rawQuery("select * from usuario where idusuario = '"+id+"'", null);
        if (buscar.moveToFirst()){
            contraA = buscar.getString(buscar.getColumnIndex("pass"));
            correoA = buscar.getString(buscar.getColumnIndex("usuario"));
            contraActual.setText(contraA);
            correoActual.setText(correoA);
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String contraN = contraNueva.getText().toString();
               String correoN = correoNuevo.getText().toString();
                String id;
                AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(view.getContext());
                SQLiteDatabase bd = alta.getWritableDatabase();

                Cursor buscar = bd.rawQuery("select * from usuario where usuario = '"+correoNuevo.getText().toString()+"'", null);



                if (correoNuevo.getText().toString().isEmpty() && contraNueva.getText().toString().isEmpty() ) {
                    Toast.makeText(view.getContext(),"Rellenar alguún campo para cambiar.", Toast.LENGTH_SHORT).show();
                }
                else if (correoNuevo.getText().toString().isEmpty()) {
                    Cursor buscar2 = bd.rawQuery("select * from usuario where usuario = '" + correoActual.getText().toString() + "'", null);
                    if (buscar2.moveToFirst()) {
                        id = buscar2.getString(buscar2.getColumnIndex("idusuario"));
                        ContentValues actualizaReg = new ContentValues();
                        actualizaReg.put("usuario", correoActual.getText().toString());
                        actualizaReg.put("pass", contraN);
                        actualizaReg.put("tipo", "escritor");
                        bd.update("usuario", actualizaReg, "idusuario=" + id, null);
                        Toast.makeText(view.getContext(), "Cambios realizados con exito.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (contraNueva.getText().toString().isEmpty()) {
                        if (buscar.moveToFirst()) {
                            Toast.makeText(view.getContext(), "Nombre de usuario no disponible.", Toast.LENGTH_SHORT).show();
                        } else {
                            Cursor buscar2 = bd.rawQuery("select * from usuario where usuario = '" + correoActual.getText().toString() + "'", null);
                            if (buscar2.moveToFirst()) {
                                id = buscar2.getString(buscar2.getColumnIndex("idusuario"));
                                ContentValues actualizaReg = new ContentValues();
                                actualizaReg.put("usuario", correoN);
                                actualizaReg.put("pass", contraActual.getText().toString());
                                actualizaReg.put("tipo", "escritor");
                                bd.update("usuario", actualizaReg, "idusuario=" + id, null);
                                Toast.makeText(view.getContext(), "Cambios realizados con exito.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                    Cursor buscar2 = bd.rawQuery("select * from usuario where usuario = '" + correoActual.getText().toString() + "'", null);
                    if (buscar2.moveToFirst()) {
                        id = buscar2.getString(buscar2.getColumnIndex("idusuario"));
                        ContentValues actualizaReg = new ContentValues();
                        actualizaReg.put("usuario", correoN);
                        actualizaReg.put("pass", contraN);
                        actualizaReg.put("tipo", "escritor");
                        bd.update("usuario", actualizaReg, "idusuario=" + id, null);
                        Toast.makeText(view.getContext(), "Cambios realizados con exito.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
}


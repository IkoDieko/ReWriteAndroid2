package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReporteM extends AppCompatActivity {
    private TextView id1, estado;
    private EditText etiqueta, usuariolevanta, problema, asignado, solucionado, cerrado, solucion;
    private Button guardar, cerrar, eliminar;
    private FloatingActionButton reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_m);

        id1 = findViewById(R.id.idMant);
        estado = findViewById(R.id.EstadoMant);
        etiqueta = findViewById(R.id.etiquetaMant);
        usuariolevanta = findViewById(R.id.LevantadoMant);
        problema = findViewById(R.id.ProblemaMant);
        asignado = findViewById(R.id.AsignadoMant);
        solucionado = findViewById(R.id.SolucionadoMant);
        cerrado = findViewById(R.id.CerradoMant);
        solucion = findViewById(R.id.SolucionMant);
        guardar = findViewById(R.id.guardarReporteM);
        cerrar = findViewById(R.id.cerrarReporteM);
        eliminar = findViewById(R.id.eliminaM);
        reg = findViewById(R.id.regresaRM);

        Intent i = getIntent();
        String tipo = i.getStringExtra("tipo3");
        String id = i.getStringExtra("idFolio");

        AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteM.this);
        SQLiteDatabase bd = buscar.getWritableDatabase();

        Cursor validar = bd.rawQuery("select * from reporteM where idreporteM = '"+id+"'", null);
        if (validar.moveToFirst()) {
            String estado1 = validar.getString(validar.getColumnIndex("estado"));
            String problema1 = validar.getString(validar.getColumnIndex("problema"));
            String etiqueta1 = validar.getString(validar.getColumnIndex("etiqueta"));
            String idLevanto1 = validar.getString(validar.getColumnIndex("idlevanta1"));
            String idasignado1 = validar.getString(validar.getColumnIndex("idasignado1"));
            String idsoluciona1 = validar.getString(validar.getColumnIndex("idsoluciona1"));
            String idcierra1 = validar.getString(validar.getColumnIndex("idcierra1"));
            String soluc = validar.getString(validar.getColumnIndex("solucion"));
            String usulevanto1 = "";
            String usulasigno1 = "";
            String ususoluciona1 = "";
            String usucierra1 = "";
            Cursor validar2 = bd.rawQuery("select * from usuario where idusuario = '" + idLevanto1 + "'", null);
            if (validar2.moveToFirst()) {
                usulevanto1 = validar2.getString(validar2.getColumnIndex("usuario"));
            }
            Cursor validar3 = bd.rawQuery("select * from usuario where idusuario = '" + idasignado1 + "'", null);
            if (validar3.moveToFirst()) {
                usulasigno1 = validar3.getString(validar3.getColumnIndex("usuario"));
            }
            Cursor validar4 = bd.rawQuery("select * from usuario where idusuario = '" + idsoluciona1 + "'", null);
            if (validar4.moveToFirst()) {
                ususoluciona1 = validar4.getString(validar4.getColumnIndex("usuario"));
            }
            Cursor validar5 = bd.rawQuery("select * from usuario where idusuario = '" + idcierra1 + "'", null);
            if (validar5.moveToFirst()) {
                usucierra1 = validar5.getString(validar5.getColumnIndex("usuario"));
            }
            id1.setText(id);
            estado.setText(estado1);
            etiqueta.setText(etiqueta1);
            usuariolevanta.setText(usulevanto1);
            problema.setText(problema1);
            asignado.setText(usulasigno1);
            solucion.setText(soluc);
            solucionado.setText(ususoluciona1);
            cerrado.setText(usucierra1);
        }
        usuariolevanta.setEnabled(false);

    }
}

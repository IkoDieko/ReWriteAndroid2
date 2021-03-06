package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            String idEvento = validar.getString(validar.getColumnIndex("idrepEvento"));

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

            if (idEvento != null){
                cerrar.setVisibility(View.INVISIBLE);
            }

        }
        usuariolevanta.setEnabled(false);





        if (tipo.equals("ingenieroM") || tipo.equals("programador")){
            estado.setEnabled(false);
            etiqueta.setEnabled(false);
            cerrado.setEnabled(false);
            asignado.setEnabled(false);
            solucionado.setEnabled(false);
            guardar.setText("Guardar Solución");
        }
        else if (tipo.equals("gerenteM")){
            estado.setEnabled(false);
            etiqueta.setEnabled(false);
            cerrado.setEnabled(false);
            solucionado.setEnabled(false);
            solucion.setEnabled(false);
        }

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String id2 = i.getStringExtra("idFolio");
                AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteM.this);
                SQLiteDatabase bd = buscar.getWritableDatabase();
                bd.delete("reporteM", "idreporteM = "+id2, null);
                Toast.makeText(ReporteM.this,"Reporte eliminado.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String tipo = i.getStringExtra("tipo3");
                String id2 = i.getStringExtra("idFolio");
                String idinge = i.getStringExtra("idusuario");
                String usuasignado = asignado.getText().toString();
                String problem = problema.getText().toString();
                AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteM.this);
                SQLiteDatabase bd = buscar.getWritableDatabase();

                if (tipo.equals("gerenteM")){
                    Cursor validar = bd.rawQuery("select * from usuario where usuario = '"+usuasignado+"' and tipo = 'ingenieroM' or tipo = 'programador'", null);
                    if (validar.moveToFirst()){
                        String idasignado = validar.getString(validar.getColumnIndex("idusuario"));
                        ContentValues cv = new ContentValues();
                        cv.put("idasignado1", idasignado);
                        cv.put("estado", "Asignado");
                        cv.put("problema", problem);
                        bd.update("reporteM", cv, "idreporteM = "+id2, null);
                        Toast.makeText(ReporteM.this,"Reporte asignado con exito.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                            Toast.makeText(ReporteM.this,"No se han encontrado ingenieros o programadores con ese nombre de usuario.", Toast.LENGTH_SHORT).show();
                        }
                }
                else if (tipo.equals("ingenieroM") || tipo.equals("programador")){
                    if (estado.getText().toString().equals("Asignado") || estado.getText().toString().equals("Solucionado")){
                        String solu = solucion.getText().toString();
                            ContentValues cv = new ContentValues();
                            cv.put("idsoluciona1", idinge);
                            if (solu.isEmpty() == false){
                                cv.put("solucion", solu);
                                cv.put("estado", "Resuelto");

                                Cursor validar = bd.rawQuery("select * from reporteM where idreporteM = '"+id2+"'", null);
                                if (validar.moveToFirst()){
                                    String idEvento = validar.getString(validar.getColumnIndex("idrepEvento"));
                                    if (idEvento != null){
                                        Cursor validar2 = bd.rawQuery("select * from reporteE where idreporteE = '"+idEvento+"'", null);
                                        if (validar2.moveToFirst()){{
                                            ContentValues cv2 = new ContentValues();
                                            cv2.put("estado", "Solucionado");
                                            bd.update("reporteE", cv2, "idreporteE = "+idEvento, null);
                                        }}
                                    }
                                }

                            }
                            cv.put("problema", problem);
                            bd.update("reporteM", cv, "idreporteM = "+id2, null);
                            Toast.makeText(ReporteM.this,"Reporte guardado con exito.", Toast.LENGTH_SHORT).show();


                        finish();

                    } else if (estado.getText().toString().equals("Cerrado")) {
                        Toast.makeText(ReporteM.this,"Reporte cerrado.", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(ReporteM.this,"Reporte no asignado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado.getText().toString().equals("Resuelto")){
                    Intent i = getIntent();
                    String tipo = i.getStringExtra("tipo3");
                    String id2 = i.getStringExtra("idFolio");
                    String idusu = i.getStringExtra("idusuario");
                    AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteM.this);
                    SQLiteDatabase bd = buscar.getWritableDatabase();

                    ContentValues cv = new ContentValues();
                    cv.put("idcierra1", idusu);
                    cv.put("estado", "Cerrado");
                    bd.update("reporteM", cv, "idreporteM = "+id2, null);
                    Toast.makeText(ReporteM.this,"Reporte cerrado con exito.", Toast.LENGTH_SHORT).show();
                    finish();

                }  else {
                    Toast.makeText(ReporteM.this,"No se ha solucionado este reporte.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}

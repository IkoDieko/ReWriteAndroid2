package com.example.rewrite2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReporteEvento extends AppCompatActivity {
    private TextView id1, estado;
    private EditText etiqueta, usuariolevanta, problema, asignado, solucionado, cerrado, solucion;
    private Button guardar, cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_evento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id1 = findViewById(R.id.editIdEvent);
        estado = findViewById(R.id.editEstadoEv);
        etiqueta = findViewById(R.id.editEtoqueta);
        usuariolevanta = findViewById(R.id.txtLevantado);
        problema = findViewById(R.id.editProblema);
        guardar = findViewById(R.id.guardarReporteE);
        cerrar = findViewById(R.id.cerrarReporteE);
        asignado = findViewById(R.id.txtAsignaReporte);
        solucionado = findViewById(R.id.txtSolucionaReporte);
        cerrado = findViewById(R.id.txtCerrado);
        solucion = findViewById(R.id.txtsolucion);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent i = getIntent();
        String tipo = i.getStringExtra("tipo3");
        String id = i.getStringExtra("idFolio");

        AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteEvento.this);
        SQLiteDatabase bd = buscar.getWritableDatabase();

        Cursor validar = bd.rawQuery("select * from reporteE where idreporteE = '"+id+"'", null);
        if (validar.moveToFirst()){
            String estado1 = validar.getString(validar.getColumnIndex("estado"));
            String problema1 = validar.getString(validar.getColumnIndex("problema"));
            String etiqueta1 = validar.getString(validar.getColumnIndex("etiqueta"));
            String idLevanto1 = validar.getString(validar.getColumnIndex("idlevanta"));
            String idasignado1 = validar.getString(validar.getColumnIndex("idasignado"));
            String idsoluciona1 = validar.getString(validar.getColumnIndex("idsoluciona"));
            String idcierra1 = validar.getString(validar.getColumnIndex("idcierra"));
            String soluc = validar.getString(validar.getColumnIndex("solucion"));
            String usulevanto1 = "";
            String usulasigno1 = "";
            String ususoluciona1 ="";
            String usucierra1 = "";
            Cursor validar2 = bd.rawQuery("select * from usuario where idusuario = '"+idLevanto1+"'", null);
            if(validar2.moveToFirst()){
                usulevanto1 =  validar2.getString(validar2.getColumnIndex("usuario"));
            }
            Cursor validar3 = bd.rawQuery("select * from usuario where idusuario = '"+idasignado1+"'", null);
            if(validar3.moveToFirst()){
                usulasigno1 =  validar3.getString(validar3.getColumnIndex("usuario"));
            }
            Cursor validar4 = bd.rawQuery("select * from usuario where idusuario = '"+idsoluciona1+"'", null);
            if(validar4.moveToFirst()){
                ususoluciona1 =  validar4.getString(validar4.getColumnIndex("usuario"));
            }
            Cursor validar5 = bd.rawQuery("select * from usuario where idusuario = '"+idcierra1+"'", null);
            if(validar5.moveToFirst()){
                usucierra1 =  validar5.getString(validar5.getColumnIndex("usuario"));
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

        if (tipo.equals("operador")){
            estado.setEnabled(false);
            etiqueta.setEnabled(false);
            cerrado.setEnabled(false);
            problema.setEnabled(false);
            asignado.setEnabled(false);
            solucionado.setEnabled(false);
            solucion.setEnabled(false);
            guardar.setVisibility(View.INVISIBLE);
            cerrar.setVisibility(View.INVISIBLE);
        } else if (tipo.equals("gerenteE")){
            estado.setEnabled(false);
            etiqueta.setEnabled(false);
            cerrado.setEnabled(false);
            problema.setEnabled(false);
            solucionado.setEnabled(false);
            solucion.setEnabled(false);
        } else if (tipo.equals("ingenieroE")){
            estado.setEnabled(false);
            etiqueta.setEnabled(false);
            cerrado.setEnabled(false);
            problema.setEnabled(false);
            asignado.setEnabled(false);
            solucionado.setEnabled(false);
            guardar.setText("Guardar Solución");
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String tipo = i.getStringExtra("tipo3");
                String id2 = i.getStringExtra("idFolio");
                String idinge = i.getStringExtra("idusuario");
                String usuasignado = asignado.getText().toString();
                AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteEvento.this);
                SQLiteDatabase bd = buscar.getWritableDatabase();

                if (tipo.equals("gerenteE")){
                    Cursor validar = bd.rawQuery("select * from usuario where usuario = '"+usuasignado+"' and tipo = 'ingenieroE'", null);
                    if (validar.moveToFirst()){
                        String idasignado = validar.getString(validar.getColumnIndex("idusuario"));
                        ContentValues cv = new ContentValues();
                        cv.put("idasignado", idasignado);
                        cv.put("estado", "Pendiente");
                        bd.update("reporteE", cv, "idreporteE = "+id2, null);
                        Toast.makeText(ReporteEvento.this,"Reporte asignado con exito.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ReporteEvento.this,"No se han encontrado ingenieros con ese nombre de usuario.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (tipo.equals("ingenieroE")){
                    if (estado.getText().toString().equals("Pendiente") || estado.getText().toString().equals("Solucionado")){
                        String solu = solucion.getText().toString();
                        if (solu.isEmpty()){
                            Toast.makeText(ReporteEvento.this,"No se ha dado ninguna solución al reporte.", Toast.LENGTH_SHORT).show();
                        } else{
                            ContentValues cv = new ContentValues();
                            cv.put("idsoluciona", idinge);
                            cv.put("solucion", solu);
                            cv.put("estado", "Solucionado");
                            bd.update("reporteE", cv, "idreporteE = "+id2, null);
                            Toast.makeText(ReporteEvento.this,"Reporte guardado con exito.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(ReporteEvento.this,"Reporte no asignado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado.getText().toString().equals("Solucionado")){
                    Intent i = getIntent();
                    String tipo = i.getStringExtra("tipo3");
                    String id2 = i.getStringExtra("idFolio");
                    String idusu = i.getStringExtra("idusuario");
                    AdminSQLiteOpenHelper buscar = new AdminSQLiteOpenHelper(ReporteEvento.this);
                    SQLiteDatabase bd = buscar.getWritableDatabase();

                    ContentValues cv = new ContentValues();
                    cv.put("idcierra", idusu);
                    bd.update("reporteE", cv, "idreporteE = "+id2, null);
                    Toast.makeText(ReporteEvento.this,"Reporte cerrado con exito.", Toast.LENGTH_SHORT).show();
                    finish();

                } else{
                    Toast.makeText(ReporteEvento.this,"No se ha solucionado este reporte.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

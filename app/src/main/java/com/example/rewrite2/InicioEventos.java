package com.example.rewrite2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InicioEventos extends AppCompatActivity {

    private Button btnNuevo, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_eventos);

        btnNuevo = (Button) findViewById(R.id.btnAlta);
        btnVer = (Button) findViewById(R.id.btnConsulta);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioEventos.this, AltaReporte.class);
                startActivity(i);
            }
        });
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioEventos.this, InicioEventos.class);
                startActivity(i);
            }
        });


    }
}

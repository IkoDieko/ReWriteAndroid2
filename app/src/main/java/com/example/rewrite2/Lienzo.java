package com.example.rewrite2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.Scroller;

public class Lienzo extends AppCompatActivity {
    private EditText contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienzo);

        contenido = findViewById(R.id.editContenidoLienzo);

        contenido.setMovementMethod(new ScrollingMovementMethod());
    }
}

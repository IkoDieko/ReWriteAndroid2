package com.example.rewrite2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;

public class Ensayo extends AppCompatActivity {
    private EditText titulo, intro, desa, con, ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensayo);

        titulo = findViewById(R.id.editTituloEnsayo);
        intro = findViewById(R.id.editIntro);
        desa = findViewById(R.id.editDesa);
        con = findViewById(R.id.editConc);
        ref = findViewById(R.id.editRefe);

        intro.setMovementMethod(new ScrollingMovementMethod());
        desa.setMovementMethod(new ScrollingMovementMethod());
        con.setMovementMethod(new ScrollingMovementMethod());
        ref.setMovementMethod(new ScrollingMovementMethod());
    }
}

package com.example.rewrite2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FloatingActionButton recarg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recarg = findViewById(R.id.recarga);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        TextView correohead = (TextView) headerView.findViewById(R.id.correoheader);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

       Intent intent = getIntent();
       String id =intent.getStringExtra("idusuario");


        AdminSQLiteOpenHelper alta = new AdminSQLiteOpenHelper(Main.this);
        SQLiteDatabase bd = alta.getWritableDatabase();
        Cursor buscar = bd.rawQuery("select * from usuario where idusuario = '"+id+"'", null);
        String usuario = "";
        if (buscar.moveToFirst()){
            usuario = buscar.getString(buscar.getColumnIndex("usuario"));
        }
       correohead.setText(usuario);

        recarg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contenido, new FragmentoMisObras()).commit();
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_MisObras) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, new FragmentoMisObras()).commit();
        } else if (id == R.id.nav_Crear) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, new FragmentoCrear()).commit();
        } else if (id == R.id.nav_ajustes) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, new FragmentoAjustes()).commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_FAQS) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, new FAQs()).commit();
        } else if (id == R.id.nav_salir) {
            Intent i = new Intent(Main.this, Login.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

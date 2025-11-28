package com.carmen.peliculaspruebacasa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class PeliculasFavoritas extends AppCompatActivity {

    //Declarar el array de peliculas
    ArrayList<Pelicula> peliculas = new ArrayList<>();

    //Declarar la actionbar
    ActionBar actionbar;

    //Declaro el para el listview y la propia listview
    ArrayAdapter<String> adapter; // Adaptador para el ListView
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_peliculas_favoritas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Rellenar la arraylist de datos (datos.java)
        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();

        //Cambiarle el color a la actionbar y ponerle como subtitulo el numero de peliculas
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.color.titulo, getTheme()));
        //boton de volver, la funcion de este boton esta mas abajo en onOptionsItemSelected
        actionbar.setDisplayHomeAsUpEnabled(true);

        //Para que aparezca el titulo y director de la peli el el listview
        ArrayList<String> titu_dire = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            titu_dire.add(pelicula.getTitulo() + "\n" + pelicula.getDirector());
        }

        //Declarar donde está la lv y cual es
        setContentView(R.layout.activity_peliculas_favoritas);
        lv=findViewById(R.id.lv);


    }

    //funcion del boton de volver en el actionbar y el del boton mguardar del menu segundario
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ListView lv = findViewById(R.id.lv);
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        } else if (id == R.id.mguardar) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.secundario, menu);
        return true;
    }

}
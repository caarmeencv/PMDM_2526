package com.carmen.peliculas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PeliculasFavoritas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intent para que cambie de activity desde la primaria
        Intent intentFavoritos  = getIntent();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_peliculas_favoritas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Informacion del array para el rv
        Datos datos = new Datos();
        ArrayList<Pelicula> peliculas = datos.rellenaPeliculas();

        //declarar el rv
        RecyclerView rvfavoritos = findViewById(R.id.rvfavoritos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rvfavoritos.setLayoutManager(gridLayoutManager);
        AdaptadorFavoritos adaptadorFavoritos = new AdaptadorFavoritos(peliculas);
        rvfavoritos.setAdapter(adaptadorFavoritos);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondomenu, getTheme()));
        actionbar.setTitle("Peliculas");
        getWindow().setNavigationBarColor(getColor(R.color.rosa));

        //boton de volver, la funcion de este boton esta mas abajo en onOptionsItemSelected
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    //funcion del boton de volver en el actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            getOnBackPressedDispatcher().onBackPressed();
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
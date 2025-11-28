package com.carmen.peliculaspruebacasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmen.peliculaspruebacasa.AdaptadorInformacion;
import com.carmen.peliculaspruebacasa.Datos;
import com.carmen.peliculaspruebacasa.Pelicula;
import com.carmen.peliculaspruebacasa.R;

import java.util.ArrayList;

public class InformacionPeliculas extends AppCompatActivity {

    //Declarar el array de peliculas
    ArrayList<Pelicula> peliculas = new ArrayList<>();

    //Declarar el grid layout manager
    GridLayoutManager gridLayoutManager;

    //Declarar la actionbar
    ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intent para que cambie de activity desde la primaria
        Intent intentInformacion  = getIntent();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informacion_peliculas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Rellenar la arraylist de datos (datos.java)
        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();

        //Declarar el recycler view (rv1) y que aparezcan las celdas (celdapelicula.xml)
        RecyclerView rv2 = findViewById(R.id.rv2);
        gridLayoutManager = new GridLayoutManager(this, 1);
        rv2.setLayoutManager(gridLayoutManager);
        AdaptadorInformacion adaptadorInformacion = new AdaptadorInformacion(peliculas);
        rv2.setAdapter(adaptadorInformacion);

        //Cambiarle el color a la actionbar y ponerle como subtitulo el numero de peliculas
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.color.titulo, getTheme()));

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
}
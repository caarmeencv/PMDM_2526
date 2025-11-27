package com.example.peliculasprueba;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Array de peliculas
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        //Rellenar array de peliculas con los datos
        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();

        //declarar action bar y atributos de la misma
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.actionbar)));

            //para que me salga el numero de peliculas como subtitulo
            actionbar.setSubtitle(String.valueOf(peliculas.size()));

        //navigationbar
        getWindow().setNavigationBarColor(getColor(R.color.rosa));

        //declarar recycler view
        RecyclerView rv = findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(gridLayoutManager);
        AdaptadorPelicula adaptadorPelicula = new AdaptadorPelicula(peliculas);
        rv.setAdapter(adaptadorPelicula);

        //Declarar el floating action button (fazoom) y poner la accion de
        // que al ser pulsado aparece y desaparece la actionbar


    }
}
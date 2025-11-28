package com.carmen.peliculaspruebacasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declarar el array de peliculas
    ArrayList<Pelicula> peliculas = new ArrayList<>();

    //Declarar el grid layout manager
    GridLayoutManager gridLayoutManager;

    //Declarar la actionbar
    ActionBar actionbar;

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

        //Rellenar la arraylist de datos (datos.java)
        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();

        //Declarar el recycler view (rv1) y que aparezcan las celdas (celdapelicula.xml)
        RecyclerView rv1 = findViewById(R.id.rv1);
        gridLayoutManager = new GridLayoutManager(this, 1);
        rv1.setLayoutManager(gridLayoutManager);
        AdaptadorPelicula adaptadorPelicula = new AdaptadorPelicula(peliculas);
        rv1.setAdapter(adaptadorPelicula);

        //Cambiarle el color a la actionbar y ponerle como subtitulo el numero de peliculas
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.color.titulo, getTheme()));

        //para que me salga el numero de peliculas como subtitulo
        actionbar.setSubtitle("Total: " + String.valueOf(peliculas.size()));

        //Cambiarle el color a la navigationbar
        getWindow().setNavigationBarColor(getColor(R.color.rosa));

        //Declarar el floating action button (fazoom) y hacer que al ser pulsado aparezca o desaparezca la actionbar
        FloatingActionButton floatingActionButton = findViewById(R.id.fazoom);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionbar.isShowing()) {
                    actionbar.hide();
                } else {
                    actionbar.show();
                }
            }
        });

    }

    //poner los botones del menu principal
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.principal, menu);
            return true;
        }

    //poner la funcion de cada opcion del menu
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item){
            int id = item.getItemId();
            if(id == R.id.minformacion){
                //intent para que al pulsar el boton la aplicacion muestre la activity InformacionPeliculas
                Intent intentInformacion = new Intent(MainActivity.this, InformacionPeliculas.class);
                startActivity(intentInformacion);
                return true;
            } else if(id == R.id.mfavoritos){
                //intent para que al pulsar el boton la aplicacion muestre la activity PeliculasFavoritas
                Intent intentFavoritas = new Intent(MainActivity.this, PeliculasFavoritas.class);
                startActivity(intentFavoritas);
                return true;
            } else if (id == R.id.manadir) {

                return super.onOptionsItemSelected(item);
            } else if(id == R.id.mverfav){

                return true;
            } else if(id == R.id.mvista){

                return true;
            };
            return super.onOptionsItemSelected(item);
        }

}
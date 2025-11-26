package com.carmen.peliculas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

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
    ArrayList<Pelicula> peliculas;
    ArrayList<Pelicula> favoritasArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intent para que cambie de activity desde la primaria
        //Intent intentFavoritos  = getIntent();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_peliculas_favoritas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recibimos la lista completa de películas
        peliculas = (ArrayList<Pelicula>) getIntent().getSerializableExtra("peliculas");
        // Recibimos el array de favoritas
        favoritasArray = (ArrayList<Pelicula>) getIntent().getSerializableExtra("favoritasArray");
        if (favoritasArray.isEmpty() && peliculas.size() > 2) {
            favoritasArray.add(peliculas.get(2)); // posición 3
        }


        ArrayList<String> titulos = new ArrayList<>();
        for (Pelicula peli : peliculas) {
            titulos.add(peli.getTitulo() + "\n" + peli.getDirector());
        }

        ListView lv = findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, titulos);
        lv.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);

        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula p = peliculas.get(i);
            for (Pelicula f : favoritasArray) {
                if (p.getTitulo().equals(f.getTitulo()) && p.getDirector().equals(f.getDirector())) {
                    lv.setItemChecked(i, true);
                    break;
                }
            }
        }

        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondomenu, getTheme()));
        actionbar.setTitle("Peliculas");
        getWindow().setNavigationBarColor(getColor(R.color.rosa));

        //boton de volver, la funcion de este boton esta mas abajo en onOptionsItemSelected
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    //funcion del boton de volver en el actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ListView lv = findViewById(R.id.lv);
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        } else if (id == R.id.mguardar) {
            // Guardamos las favoritas seleccionadas
            favoritasArray.clear();
            for (int i = 0; i < peliculas.size(); i++) {
                if (lv.isItemChecked(i)) {
                    favoritasArray.add(peliculas.get(i));
                }
            }

            Toast.makeText(this, "Favoritas actualizadas", Toast.LENGTH_SHORT).show();

            // Devolvemos al MainActivity
            Intent intent = new Intent();
            intent.putExtra("favoritasArray", favoritasArray);
            setResult(Activity.RESULT_OK, intent);
            finish();
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
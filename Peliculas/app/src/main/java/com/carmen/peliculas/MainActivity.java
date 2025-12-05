package com.carmen.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    ArrayList<Pelicula> peliculas = new ArrayList<>();
    ArrayList<Pelicula> favoritasArray = new ArrayList<>();
    GridLayoutManager gridLayoutManager;

    boolean vista = false;

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

        Datos datos = new Datos();
        peliculas = datos.rellenaPeliculas();

        RecyclerView rv = findViewById(R.id.rv);
        gridLayoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(gridLayoutManager);
        AdaptadorPelicula adaptadorPelicula = new AdaptadorPelicula(peliculas);
        rv.setAdapter(adaptadorPelicula);

        //actionbar
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondomenu, getTheme()));

        //para que me salga el numero de peliculas como subtitulo
        actionbar.setSubtitle(String.valueOf(peliculas.toArray().length));

        //navigationbar
        getWindow().setNavigationBarColor(getColor(R.color.rosa));

        ActivityResultLauncher<Intent> nuevaPeliculaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Pelicula p = (Pelicula) result.getData().getSerializableExtra("nueva_pelicula");
                        if (p != null) {
                            peliculas.add(p);
                            adaptadorPelicula.notifyItemInserted(peliculas.size() - 1);
                            rv.scrollToPosition(peliculas.size() - 1);
                        }
                    }
                }
        );

        //para que a darle al boton de abajo a la derecha aparezca o desaparezca la actionbar
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

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Pelicula> favoritasRecibidas =
                            (ArrayList<Pelicula>) result.getData().getSerializableExtra("favoritasArray");

                    if (favoritasRecibidas != null) {
                        favoritasArray = favoritasRecibidas; // actualizamos favoritasArray directamente
                    }
                }
            }
    );


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.minformacion){
            Intent intentInformacion = new Intent(MainActivity.this, InformacionPeliculas.class);
            startActivity(intentInformacion);
            return true;
        } else if(id == R.id.manadir){
            Intent intentNuevaPeli = new Intent(MainActivity.this, NuevaPelicula.class);
            startActivity(intentNuevaPeli);
            return true;
        } else if (id == R.id.mfavoritos) {
            Intent intentFavoritos = new Intent(this, PeliculasFavoritas.class);
            intentFavoritos.putExtra("peliculas", peliculas);
            intentFavoritos.putExtra("favoritasArray", favoritasArray);
            launcher.launch(intentFavoritos);
        return super.onOptionsItemSelected(item);
        } else if(id == R.id.mverfav){

            return true;
        } else if(id == R.id.mvista){
            vista = !vista;
            gridLayoutManager.setSpanCount(vista ? 2 : 1);
            gridLayoutManager.requestLayout();
            //return true;

            if(vista){

                item.setIcon(R.drawable.ic_two);

            } else {

                item.setIcon(R.drawable.ic_one);

            }
        };
        return super.onOptionsItemSelected(item);
    }
}
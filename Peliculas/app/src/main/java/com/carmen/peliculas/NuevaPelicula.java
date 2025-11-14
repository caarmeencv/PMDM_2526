package com.carmen.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NuevaPelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intent para que cambie de activity desde la primaria
        Intent intentNuevaPeli  = getIntent();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nueva_pelicula);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

}
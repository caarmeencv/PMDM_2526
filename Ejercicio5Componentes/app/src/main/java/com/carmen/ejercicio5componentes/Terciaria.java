package com.carmen.ejercicio5componentes;

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

public class Terciaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terciaria);
        Intent intentTerciaria  = getIntent();
        Intent intentTerciaria2  = getIntent();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });

            //declarar la action bar y cambiarle el titulo y subtitulo
            ActionBar actionbar = getSupportActionBar();
            actionbar.setTitle("Ejercicio 5");
            actionbar.setSubtitle("Terciaria");

            //boton de volver en el actionbar
            actionbar.setDisplayHomeAsUpEnabled(true);

            //cambiarle el color a la navigation bar
            getWindow().setNavigationBarColor(getColor(R.color.yellow));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            Toast.makeText(this, "Vuelvo a la activity anterior", Toast.LENGTH_SHORT).show();
            getOnBackPressedDispatcher().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
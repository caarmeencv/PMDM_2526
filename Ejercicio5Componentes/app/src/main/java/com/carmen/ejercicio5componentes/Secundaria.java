package com.carmen.ejercicio5componentes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Secundaria extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secundaria);
        getWindow().setStatusBarColor(getColor(R.color.yellow));

        //recuperamos el intent enviado desde la activity primaria
        Intent intent  = getIntent();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //declaro el boton
        Button volver = findViewById(R.id.button2);
        Button irTeciaria = findViewById(R.id.button6);

        //declaro el textview
        TextView textView8 = findViewById(R.id.textView8);

        //declaro el rating bar
        RatingBar ratingbar = findViewById(R.id.ratingBar2);


        float estrellas = getIntent().getFloatExtra("numEstrellas", 0f);
        ratingbar.setRating(estrellas);

        Toast.makeText(Secundaria.this, "Rating de la Main Activity en la Secundaria", Toast.LENGTH_SHORT).show();

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(Secundaria.this, (getString(R.string.rating) + ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

        //la accion del boton para volver a la mainActivity
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Secundaria.this, MainActivity.class);
                intent.putExtra("numEstrellas", ratingbar.getRating());
                startActivity(intent);
                Toast.makeText(Secundaria.this, "Rating de la Secundaria en la Main Activity", Toast.LENGTH_SHORT).show();

            }
        });

        //la accion del boton para ir a la terciaria
        irTeciaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTerciaria = new Intent(Secundaria.this, Terciaria.class);
                startActivity(intentTerciaria);
                Toast.makeText(Secundaria.this, "Terciaria", Toast.LENGTH_SHORT).show();

            }
        });

        //declarar la action bar y cambiarle el titulo y subtitulo
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Ejercicio 5");
        actionbar.setSubtitle("Secundaria");

        //boton de volver en el actionbar
        actionbar.setDisplayHomeAsUpEnabled(true);

        //cambiarle el color a la navigation bar
        getWindow().setNavigationBarColor(getColor(R.color.yellow));

    }
    //funcion del boton de volver en el actionbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            Toast.makeText(this, "Vuelvo a la activity anterior", Toast.LENGTH_SHORT).show();
            getOnBackPressedDispatcher().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
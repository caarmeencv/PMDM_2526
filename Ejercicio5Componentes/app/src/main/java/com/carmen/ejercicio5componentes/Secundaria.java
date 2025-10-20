package com.carmen.ejercicio5componentes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Secundaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secundaria);

        //recuperamos el intent enviado desde la activity primaria
        Intent intent  = getIntent();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //declaro el boton
        Button volver = findViewById(R.id.button2);

        //declaro el textview
        TextView textView8 = findViewById(R.id.textView8);

        //declaro el rating bar
        RatingBar ratingbar = findViewById(R.id.ratingBar2);

        //declaro el ratingbar
        RatingBar ratingBar = findViewById(R.id.ratingBar2);
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
    }
}
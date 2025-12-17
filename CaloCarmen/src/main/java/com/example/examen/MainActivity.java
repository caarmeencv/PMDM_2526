package com.example.examen;

import static android.app.PendingIntent.getActivity;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Carmen Calo Vazquez
    ArrayList<Personaje> personajes;
    ActionBar actionbar;

    RecyclerView rv;
    Adaptador adaptador;

    GridLayoutManager gridLayoutManager;



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

        personajes = Personaje.getDatos();
        rv = findViewById(R.id.rvpersonajes);

        gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(gridLayoutManager);

        adaptador = new Adaptador(personajes);
        rv.setAdapter(adaptador);


        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(AppCompatResources.getDrawable(this, R.color.actionbar));

        actionbar.setTitle(R.string.tituloactionbar);
        actionbar.setSubtitle(String.valueOf(personajes.size()));

        FloatingActionButton fazoom = findViewById(R.id.fazoom);

        fazoom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (actionbar.isShowing()) {
                    actionbar.hide();
                } else {
                    actionbar.show();
                }

            }

        });


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true){
            @Override
            public void handleOnBackPressed() {
                DialogoAlerta dAlerta = new DialogoAlerta();
                dAlerta.show(getSupportFragmentManager(), "Alerta");
            }
        });


    }
    public void opcionOk(String mensaje) {
        Toast.makeText(this, R.string.positivo, Toast.LENGTH_SHORT).show();
        finish();
    }
    public void opcionCancel(String mensaje) {
        Toast.makeText(this, R.string.negativo, Toast.LENGTH_SHORT).show();
    }
    public void opcionNeutra(String mensaje) {
        Toast.makeText(this, R.string.neutro, Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.mgrid){
            if(gridLayoutManager.getSpanCount()==1) {
                gridLayoutManager.setSpanCount(2);
            } else {
                gridLayoutManager.setSpanCount(1);
            }
            return true;
        }else if (id==R.id.manadir){
            Intent intent=new Intent(MainActivity.this, AddPersonaje.class);
            startActivity(intent);
            return true;
        }else if (id==R.id.mlog){
            Toast.makeText(this, R.string.toastlog, Toast.LENGTH_SHORT).show();
            Log.i(TAG, personajes.toString());
            return true;
        };
        return super.onOptionsItemSelected(item);
    }
}

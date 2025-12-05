package com.carmen.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NuevaPelicula extends AppCompatActivity {

    EditText etTitulo, etDirector, etDuracion;
    Spinner spSala;
    RadioGroup radioGroup;
    RadioButton rbG, rbPG, rbPG13, rbR, rbNC17;
    CalendarView calendarView;

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
        actionbar.setTitle(R.string.nuevapeli);
        getWindow().setNavigationBarColor(getColor(R.color.rosa));

        //boton de volver, la funcion de este boton esta mas abajo en onOptionsItemSelected
        actionbar.setDisplayHomeAsUpEnabled(true);

        //declaro lo del layout
        etTitulo = findViewById(R.id.etTitulo);
        etDirector = findViewById(R.id.etDirector);
        etDuracion = findViewById(R.id.etDuracion);
        spSala = findViewById(R.id.spSala);
        radioGroup = findViewById(R.id.radioGroup);
        rbG = findViewById(R.id.rbG);
        rbPG = findViewById(R.id.rbPG);
        rbPG13 = findViewById(R.id.rbPG13);
        rbR = findViewById(R.id.rbR);
        rbNC17 = findViewById(R.id.rbNC17);
        calendarView = findViewById(R.id.calendarView);





    }

    //funcion del boton de volver en el actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home){
            getOnBackPressedDispatcher().onBackPressed();
        } else if (id == R.id.mguardar){
            guardarYDevolver();
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
    private void guardarYDevolver() {
        String titulo = etTitulo.getText().toString().trim();
        String director = etDirector.getText().toString().trim();
        String duracionStr = etDuracion.getText().toString().trim();

        if (titulo.isEmpty()) {
            etTitulo.setError("Introduce título");
            etTitulo.requestFocus();
            return;
        }

        int duracion = 0;
        if (!duracionStr.isEmpty()) {
            try {
                duracion = Integer.parseInt(duracionStr);
            } catch (NumberFormatException e) {
                etDuracion.setError("Duración inválida");
                etDuracion.requestFocus();
                return;
            }
        }

        String sala = spSala.getSelectedItem() != null ? spSala.getSelectedItem().toString() : "";

        int selectedRbId = radioGroup.getCheckedRadioButtonId();
        String clasificacion = "";
        if (selectedRbId != -1) {
            RadioButton rb = findViewById(selectedRbId);
            if (rb != null) clasificacion = rb.getText().toString();
        }

        long estrenoMillis = calendarView.getDate();

        Pelicula nueva = new Pelicula(titulo, director, duracion, sala, clasificacion);

        Intent result = new Intent();
        result.putExtra("nueva_pelicula", nueva);
        setResult(RESULT_OK, result);
        Toast.makeText(this, "Película guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}
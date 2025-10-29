package com.carmen.ejercicio5componentes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        //declarar la variable del togglebutton
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        //declarar la variable de las checkbox
        CheckBox checkBox = findViewById(R.id.checkBox);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);

        //declarar la variable de los botones
        Button button = findViewById(R.id.button);
        Button button4 = findViewById(R.id.button4);
        Button button3 = findViewById(R.id.button3);
        ImageButton imageButton = findViewById(R.id.imageButton);

        //declarar la variable de la ratingbar
        RatingBar ratingbar = findViewById(R.id.ratingBar);

        //declarar la variable de la seekbar
        SeekBar seekBar = findViewById(R.id.seekBar);

        //declarar la variable del edittext
        EditText editText = findViewById(R.id.editTextTextEmailAddress);

        //declarar la variable del switch
        Switch switch1 = findViewById(R.id.switch1);

        //declarar la variable de los radiobutton
        RadioButton radioButton = findViewById(R.id.radioButton);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //declarar la variable de los textview
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView = findViewById(R.id.textView);
        TextView textView4 = findViewById(R.id.textView4);

        //declarar la actionbar
        ActionBar actionbar = getSupportActionBar();

        //la funcion del boton de arriba que funcionara como un boton de reseteo
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox3.setChecked(false);
                checkBox2.setChecked(false);
                checkBox.setChecked(false);
                radioButton2.setChecked(false);
                radioButton.setChecked(false);
                ratingbar.setRating(0);
                editText.setText(null);
                seekBar.setProgress(0);
                toggleButton.setChecked(false);
                switch1.setChecked(false);
                textView3.setText(String.valueOf(0));
            }
        });

        //la funcion del boton con imagen para que aparezca un contador adyacente
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int incremento = 1;

                if (!checkBox2.isChecked()) incremento = 1;
                else incremento = -1;

                int valor = 0;
                try {
                    valor = Integer.parseInt(textView3.getText().toString()) + incremento;
                } catch (NumberFormatException ex) {
                    valor = 0;
                }
                textView3.setText(String.valueOf(valor));


                    /*
                      esta es una solucion alternativa:

                        int contador=0;
                        if (textView3.getText().equals("ImageButton y botón con icono")) {
                            //contador = 0;
                            textView3.setText(String.valueOf(contador));
                        } else {
                            contador = Integer.parseInt(textView3.getText().toString());
                            contador+=incremento;
                            textView3.setText(String.valueOf(contador));
                        }
                    */

                    /*
                      Esta es la solucion que hice inicialmente

                        int contador=0;
                        if(!checkBox2.isChecked()) {
                                if (textView3.getText().equals("ImageButton y botón con icono")) {
                                    //contador = 0;
                                    textView3.setText(String.valueOf(contador));
                                } else {
                                    contador = Integer.parseInt(textView3.getText().toString());
                                    contador++;
                                    textView3.setText(String.valueOf(contador));
                                }
                        } else {
                                if (textView3.getText().equals("ImageButton y botón con icono")) {
                                    contador = 0;
                                    textView3.setText(String.valueOf(contador));
                                } else {
                                    contador = Integer.parseInt(textView3.getText().toString());
                                    contador--;
                                    textView3.setText(String.valueOf(contador));
                                }
                        }
                     */
            }
        });

        //que aparezca un toast al marcar un radio button informando de cual esta activado
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if (radioButton.isChecked()) {
                    Toast.makeText(MainActivity.this, getString(R.string.uno), Toast.LENGTH_SHORT).show();
                } else if (radioButton2.isChecked()) {
                    Toast.makeText(MainActivity.this, getString(R.string.dos), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //funcion de la seekbar para que el textview muestre su valor
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView5.setText(String.valueOf(progress));
                actionbar.setSubtitle(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //la funcion del togglebutton para que segun este activo o desactivo cambie el estado del checkbox
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkBox3.setChecked(!isChecked);

                    /*
                    esta es la solucion que hice yo

                    if (isChecked) {
                        checkBox3.setChecked(true);
                    } else {
                        checkBox3.setChecked(false);
                    }
                    */

            }
        });

        //la funcion del switch para que cambie el texto a activo o desactivo
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                textView7.setText(isChecked ? getString(R.string.activo) : getString(R.string.desactivo));

                    /*
                    Esta es la solucion que tenia yo:

                    if (isChecked) {
                        textView7.setText("Activo");
                    } else {
                        textView7.setText("Desactivo");
                    }
                    */


            }
        });

        float estrellas = getIntent().getFloatExtra("numEstrellas", 0f);
        ratingbar.setRating(estrellas);

        //metodo para que al darle al imagebutton me abra la activity secundaria
        imageButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intentSec = new Intent(MainActivity.this, Secundaria.class);
                   intentSec.putExtra("numEstrellas", ratingbar.getRating());
                   startActivity(intentSec);
               }
           }
        );

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, (getString(R.string.rating) + ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

        //hacer que el boton 3 me la oculte o me la muestre

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionbar.isShowing()){
                    actionbar.hide();
                } else{
                    actionbar.show();
                }
            }
        });

        //poner un titulo y subtitulo
        actionbar.setTitle("Ejercicio 5 - primaria");


        //poner un logo en la actionbar
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setLogo(android.R.drawable.star_big_on);

        //cambiarle el color a la status bar
        getWindow().setStatusBarColor(getColor(R.color.yellow));

        //cambiarle el color a la navigation bar
        getWindow().setNavigationBarColor(getColor(R.color.yellow));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.principal, menu);

        Button button5 = menu.findItem(R.id.app_bar_switch).getActionView().findViewById(R.id.button5);

        //Se gestiona la pulsacion del boton
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this, "Boton del menu pulsado", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.mcopiar){
            Toast.makeText(this, "Botón del menú para copiar", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.mborrar){
            Toast.makeText(this, "Botón del menú para borrar", Toast.LENGTH_SHORT).show();
            //declarar la variable de los textview
            TextView textView7 = findViewById(R.id.textView7);
            TextView textView5 = findViewById(R.id.textView5);
            TextView textView3 = findViewById(R.id.textView3);
            TextView textView6 = findViewById(R.id.textView6);
            TextView textView = findViewById(R.id.textView);
            TextView textView4 = findViewById(R.id.textView4);
            SeekBar seekBar = findViewById(R.id.seekBar);

            seekBar.setProgress(0);
            textView.setText(null);
            textView6.setText(null);
            textView3.setText(null);
            textView5.setText(null);
            textView7.setText(null);
            textView4.setText(null);
            return true;
        }
        else if(id == R.id.mpegar){
            Toast.makeText(this, "Botón del menú para pegar", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.msubmenu){
            Toast.makeText(this, "Botón para abrir el submenu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.mopcion1){
            Toast.makeText(this, "Opción 1 del submenu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.mopcion2){
            Toast.makeText(this, "Opción 2 del submenu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id== R.id.meditar){
            Toast.makeText(this, "Botón del menú para editar", Toast.LENGTH_SHORT).show();
            EditText editText = findViewById(R.id.editTextTextEmailAddress);
            editText.setText(null);
            return true;
        }
        else if (id == R.id.mnuevo){
            Intent intentTerciaria2 = new Intent(MainActivity.this, Terciaria.class);
            startActivity(intentTerciaria2);
            Toast.makeText(MainActivity.this, "Terciaria", Toast.LENGTH_SHORT).show();
            return true;
        };
        return super.onOptionsItemSelected(item);
    }
}
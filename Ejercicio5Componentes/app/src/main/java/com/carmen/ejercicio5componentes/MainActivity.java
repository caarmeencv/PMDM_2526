package com.carmen.ejercicio5componentes;

import android.os.Bundle;
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
            ImageButton imageButton = findViewById(R.id.imageButton);
            Button button4 = findViewById(R.id.button4);

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

            //la funcion del boton de arriba que funcionara como un boton de reseteo
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    checkBox3.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox.setChecked(false);


                }
            });

            //la funcion del togglebutton para que segun este activo o desactivo cambie el estado del checkbox
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        checkBox3.setChecked(true);
                    } else {
                        checkBox3.setChecked(false);
                    }
                }
            });

            //la funcion del switch para que cambie el texto a activo o desactivo
            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            textView7.setText("Activo");
                        } else {
                            textView7.setText("Desactivo");
                        }
                    }
            });
    }
}
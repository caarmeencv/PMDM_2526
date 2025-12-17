package com.example.examen;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoAlerta extends DialogFragment {

    //Carmen Calo Vazquez

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle(R.string.salir).setMessage(R.string.preguntasalir).setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton(R.string.salirrespuesta, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                ((MainActivity)getActivity()).opcionOk(getString(R.string.salirrespuesta));
            }
        });

        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id) {
                ((MainActivity)getActivity()).opcionCancel(getString(R.string.cancelar));
            }
        });

        builder.setNeutralButton(R.string.postponer, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id) {
                ((MainActivity)getActivity()).opcionNeutra(getString(R.string.postponer));
            }
        });

        return builder.create();
    }
}


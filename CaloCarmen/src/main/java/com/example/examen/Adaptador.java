package com.example.examen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.celdajava>{

    //Carmen Calo Vazquez
    ArrayList<Personaje> personajes;

    public Adaptador(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    int selectedPos=RecyclerView.NO_POSITION;
    public int getSelectedPos() {
        return selectedPos;
    }
    public void setSelectedPos(int nuevaPos) {
    // Si se pulsa sobre el elemento marcado
        if (nuevaPos == this.selectedPos){

        } else { // El elemento pulsado no está marcado
            if (this.selectedPos >=0 ) { // Si ya hay otra posición marcada
            // Se desmarca
                notifyItemChanged(this.selectedPos);
            }
            // Se actualiza la nueva posición a la posición pulsada
            this.selectedPos = nuevaPos;
            // Se marca la nueva posición
            notifyItemChanged(nuevaPos);
        }
    }


    @NonNull
    @Override
    public celdajava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celda = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda, parent, false);
        celdajava celdajava = new celdajava(celda);
        return celdajava;
    }

    @Override
    public void onBindViewHolder(@NonNull celdajava holder, int position) {
        Personaje personaje = this.personajes.get(position);
        holder.nombre.setText(personaje.getNombre());
        holder.tipo.setText(personaje.getTipo());
        holder.imagen.setImageResource(personaje.getImagen());
        holder.orden.setText(String.valueOf(position + 1));

        if (selectedPos == position) {
            holder.itemView.setBackgroundResource(R.color.fondoseleccionado);
        } else {
            holder.itemView.setBackgroundResource(R.color.fondocelda);
        }
    }

    @Override
    public int getItemCount() {
        return this.personajes.size();
    }

    public class celdajava extends RecyclerView.ViewHolder{

        TextView nombre, tipo, orden;
        ImageView imagen;

        public celdajava(@NonNull View itemView) {
            super(itemView);
            this.nombre=itemView.findViewById(R.id.tvnombre);
            this.tipo=itemView.findViewById(R.id.tvtipo);
            this.imagen=itemView.findViewById(R.id.ivimagen);
            this.orden=itemView.findViewById(R.id.tvorden);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // getAdapterPosition devuelve la posición del view en el adaptador
                    int posPulsada=getAdapterPosition();
                    setSelectedPos(posPulsada);

                    if (selectedPos>RecyclerView.NO_POSITION){

                    }
                }
            });

        }

        public TextView getNombre() {
            return nombre;
        }

        public void setNombre(TextView nombre) {
            this.nombre = nombre;
        }

        public TextView getTipo() {
            return tipo;
        }

        public void setTipo(TextView tipo) {
            this.tipo = tipo;
        }

        public ImageView getImagen() {
            return imagen;
        }

        public void setImagen(ImageView imagen) {
            this.imagen = imagen;
        }

        public TextView getOrden() {
            return orden;
        }

        public void setOrden(TextView orden) {
            this.orden = orden;
        }
    }
}

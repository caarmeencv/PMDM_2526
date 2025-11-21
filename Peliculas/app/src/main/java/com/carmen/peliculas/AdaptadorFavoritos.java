package com.carmen.peliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorFavoritos extends RecyclerView.Adapter<AdaptadorFavoritos.celdaFavoritosjava>{

    List<Pelicula> peliculas;
    public AdaptadorFavoritos (List<Pelicula> peliculas){
        this.peliculas = peliculas;
    }

    //para seleccionar elementos de la rv
    int selectedPos = RecyclerView.NO_POSITION;
    public int getSelectedPos () {
        return selectedPos;
    }
    public void setSelectedPos(int nuevaPos) {
        // Si se pulsa sobre el elemento marcado
        if (nuevaPos == this.selectedPos){
            // Se establece que no hay una posición marcada
            this.selectedPos=RecyclerView.NO_POSITION;
            // Se avisa al adaptador para que desmarque esa posición
            notifyItemChanged(nuevaPos);
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
    public AdaptadorFavoritos.celdaFavoritosjava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celda= LayoutInflater.from(parent.getContext()).inflate(R.layout.celdafavoritos, parent, false);
        AdaptadorFavoritos.celdaFavoritosjava celdaFavoritosjava = new AdaptadorFavoritos.celdaFavoritosjava(celda);
        return celdaFavoritosjava;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFavoritos.celdaFavoritosjava holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.titulo.setText(pelicula.getTitulo());
        holder.director.setText(pelicula.getDirector());

        //para seleccionar un elemento
        if (selectedPos == position) {
            holder.itemView.setBackgroundResource(R.color.seleccionfavoritos);

        } else {
            holder.itemView.setBackgroundResource(R.color.fondo);
        }

        //para que si estan favoritas el checkbox aparezca marcado
        holder.cbfavoritos.setChecked(pelicula.getFavorita());
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class celdaFavoritosjava extends RecyclerView.ViewHolder {

        TextView titulo, director;
        CheckBox cbfavoritos;

        public celdaFavoritosjava(@NonNull View itemView) {
            super(itemView);
            this.titulo=itemView.findViewById(R.id.tvtitulo3);
            this.director=itemView.findViewById(R.id.tvdirector3);
            this.cbfavoritos = itemView.findViewById(R.id.cbFavoritos);

            // marcar desmarcar el cbfavoritos
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cbfavoritos.setChecked(!cbfavoritos.isChecked());
                }
            });

        }

        public TextView getTitulo() {
            return titulo;
        }

        public void setTitulo(TextView titulo) {
            this.titulo = titulo;
        }

        public TextView getDirector() {
            return director;
        }

        public void setDirector(TextView director) {
            this.director = director;
        }



    }
}

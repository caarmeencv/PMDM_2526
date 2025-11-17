package com.carmen.peliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorFavoritos extends RecyclerView.Adapter<AdaptadorFavoritos.celdaFavoritosjava>{

    List<Pelicula> peliculas;
    public AdaptadorFavoritos (List<Pelicula> peliculas){
        this.peliculas = peliculas;
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
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class celdaFavoritosjava extends RecyclerView.ViewHolder {

        TextView titulo, director;

        public celdaFavoritosjava(@NonNull View itemView) {
            super(itemView);
            this.titulo=itemView.findViewById(R.id.tvtitulo3);
            this.director=itemView.findViewById(R.id.tvdirector3);

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

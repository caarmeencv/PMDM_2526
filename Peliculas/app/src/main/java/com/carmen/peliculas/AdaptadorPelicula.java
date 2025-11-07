package com.carmen.peliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPelicula extends RecyclerView.Adapter<AdaptadorPelicula.celdaPeliculajava>{

    List<Pelicula> peliculas;
    public AdaptadorPelicula (List<Pelicula> peliculas){
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public celdaPeliculajava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celda= LayoutInflater.from(parent.getContext()).inflate(R.layout.celdapelicula, parent, false);
        celdaPeliculajava celdaPeliculajava = new celdaPeliculajava(celda);
        return celdaPeliculajava;
    }

    @Override
    public void onBindViewHolder(@NonNull celdaPeliculajava holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.portada.setImageResource(pelicula.getPortada());
        holder.clasi.setImageResource(pelicula.getClasi());
        holder.titulo.setText(pelicula.getTitulo());
        holder.director.setText(pelicula.getDirector());
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class celdaPeliculajava extends RecyclerView.ViewHolder {

        TextView titulo, director;
        ImageView portada, clasi;

        public celdaPeliculajava(@NonNull View itemView) {
            super(itemView);
            this.titulo=itemView.findViewById(R.id.tvtitulo);
            this.director=itemView.findViewById(R.id.tvdirector);
            this.portada=itemView.findViewById(R.id.ivimagen);
            this.clasi=itemView.findViewById(R.id.ivclasi);

        }

        public TextView getTitulo() {
            return titulo;
        }

        public void setTitulo(TextView titulo) {
            this.titulo = titulo;
        }

        public TextView getDirector() {
            return titulo;
        }

        public void setDirector(TextView director) {
            this.director = director;
        }

        public ImageView getPortada() {
            return portada;
        }

        public void setPortada(ImageView portada) {
            this.portada = portada;
        }

        public ImageView getClasi() {
            return clasi;
        }

        public void setClasi(ImageView clasi) {
            this.clasi = clasi;
        }

    }
}

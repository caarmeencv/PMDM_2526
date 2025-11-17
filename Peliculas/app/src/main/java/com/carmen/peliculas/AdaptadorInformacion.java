package com.carmen.peliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdaptadorInformacion extends RecyclerView.Adapter<AdaptadorInformacion.celdaInformacionjava>{

    List<Pelicula> peliculas;
    public AdaptadorInformacion (List<Pelicula> peliculas){
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public AdaptadorInformacion.celdaInformacionjava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celda= LayoutInflater.from(parent.getContext()).inflate(R.layout.celdainformacion, parent, false);
        AdaptadorInformacion.celdaInformacionjava celdaInformacionjava = new AdaptadorInformacion.celdaInformacionjava(celda);
        return celdaInformacionjava;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorInformacion.celdaInformacionjava holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.portada.setImageResource(pelicula.getPortada());
        holder.clasi.setImageResource(pelicula.getClasi());
        holder.titulo.setText(pelicula.getTitulo());
        holder.director.setText(pelicula.getDirector());
        holder.duracion.setText(String.valueOf(pelicula.getDuracion()));
        holder.sala.setText(pelicula.getSala());
        //holder.fecha.setText(String.valueOf(pelicula.getFecha()));
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        holder.fecha.setText(dateformat.format(pelicula.getFecha())) ;
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class celdaInformacionjava extends RecyclerView.ViewHolder {

        TextView titulo, director, duracion, sala, fecha;
        ImageView portada, clasi;

        public celdaInformacionjava(@NonNull View itemView) {
            super(itemView);
            this.titulo=itemView.findViewById(R.id.tvtitulo2);
            this.director=itemView.findViewById(R.id.tvdirector2);
            this.portada=itemView.findViewById(R.id.ivimagen2);
            this.clasi=itemView.findViewById(R.id.ivclasi2);
            this.duracion=itemView.findViewById(R.id.tvduracion2);
            this.sala=itemView.findViewById(R.id.tvsala2);
            this.fecha=itemView.findViewById(R.id.tvfecha2);

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

        public TextView getDuracion() {
            return duracion;
        }

        public void setDuracion(TextView duracion) {
            this.duracion = duracion;
        }

        public TextView getSala() {
            return sala;
        }

        public void setSala(TextView sala) {
            this.sala = sala;
        }

        public TextView getFecha() {
            return fecha;
        }

        public void setFecha(TextView fecha) {
            this.fecha = fecha;
        }


    }
}

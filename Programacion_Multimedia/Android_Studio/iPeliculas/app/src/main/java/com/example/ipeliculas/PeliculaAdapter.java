package com.example.ipeliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {
    private List<Pelicula> listaPeliculas;
    private Context context;


    public PeliculaAdapter(List<Pelicula> listaPeliculas, Context context) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloTextView;
        public TextView sinopsisTextView;
        public ImageView imgPortada;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = listaPeliculas.get(position);

        holder.tituloTextView.setText(pelicula.getTitulo());
        holder.sinopsisTextView.setText(pelicula.getSinopsis());
        holder.imgPortada.setImageResource(Integer.parseInt(pelicula.getImagen()));

/*        Glide.with(context)
                .load(pelicula.getImagen())
                .into(holder.imgPortada);

        if (pelicula.getImagen() != null) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_item);
            holder.imgPortada.startAnimation(animation);
        }*/

    }

    @Override
    public int getItemCount() {
        return 0;
    }





}

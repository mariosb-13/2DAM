package com.example.ipeliculas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {
    private List<Pelicula> listaPeliculas;
    private Context context;


    public PeliculaAdapter(List<Pelicula> listaPeliculas, Context context) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloTextView,generoTextView;
        public ImageView imgPortada;
        public RatingBar ratingBar;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.tvTitulo);
            generoTextView = itemView.findViewById(R.id.tvGenero);
            imgPortada = itemView.findViewById(R.id.imgPortada);
            ratingBar = itemView.findViewById(R.id.ratingBar);
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
        holder.generoTextView.setText(pelicula.getGenero());
        holder.ratingBar.setRating(pelicula.getValoracion());

        Glide.with(context)
                .load(pelicula.getImagen())
                .placeholder(R.drawable.ic_launcher_background) // Imagen mientras carga
                .error(R.drawable.ic_launcher_background)       // Imagen si falla la URL
                .into(holder.imgPortada);


        if (holder.itemView.getAnimation() == null) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            holder.itemView.startAnimation(animation);
        }

        if (holder.itemView.getAnimation() == null) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            holder.itemView.startAnimation(animation);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallePeliculaActivity.class);

            // Meter los datos en el intent (putExtra)
            intent.putExtra("TITULO", pelicula.getTitulo());
            intent.putExtra("GENERO", pelicula.getGenero());
            intent.putExtra("SINOPSIS", pelicula.getSinopsis());
            intent.putExtra("IMAGEN", pelicula.getImagen()); // Pasamos el ID (int)
            intent.putExtra("VALORACION", pelicula.getValoracion());

            // Iniciar la actividad
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

}

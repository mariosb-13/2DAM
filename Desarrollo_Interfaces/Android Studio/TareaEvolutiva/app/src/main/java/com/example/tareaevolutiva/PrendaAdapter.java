package com.example.tareaevolutiva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder> {

    private final List<Integer> mDataSet;

    /**
     * Constructor
     * @param dataSet Lista de IDs de recursos (R.drawable.nombre_imagen)
     */
    public PrendaAdapter(List<Integer> dataSet) {
        mDataSet = dataSet;
    }

    // El ViewHolder contiene las vistas para cada elemento de la lista
    public static class PrendaViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imgPrenda;

        public PrendaViewHolder(View view) {
            super(view);
            // El layout item_prenda.xml solo tiene un ImageButton
            imgPrenda = (ImageButton) view;
        }
    }

    @NonNull
    @Override
    public PrendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout item_prenda.xml
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_prenda, parent, false);
        return new PrendaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrendaViewHolder holder, int position) {
        // Establece la imagen de la prenda usando el ID del recurso
        holder.imgPrenda.setImageResource(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
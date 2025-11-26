package com.example.tareaevolutiva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder> {

    private final List<Integer> mPrendas;
    private final int mLayoutResId; // Guarda el ID del layout específico

    public PrendaAdapter(List<Integer> prendas, int layoutResId) {
        this.mPrendas = prendas;
        this.mLayoutResId = layoutResId;
    }

    @NonNull
    @Override
    public PrendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla la vista usando el layout específico (mLayoutResId)
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutResId, parent, false);
        return new PrendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrendaViewHolder holder, int position) {
        int drawableResId = mPrendas.get(position);
        holder.imageViewPrenda.setImageResource(drawableResId);
    }

    @Override
    public int getItemCount() {
        return mPrendas.size();
    }

    public static class PrendaViewHolder extends RecyclerView.ViewHolder {
        // Asume que todos los layouts de ítem usan el ID 'image_prenda'
        ImageView imageViewPrenda;

        public PrendaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPrenda = itemView.findViewById(R.id.image_prenda);
        }
    }
}
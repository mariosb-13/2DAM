package es.iescarrillo.diseofigma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostreAdapter extends RecyclerView.Adapter<PostreAdapter.PostreViewHolder> {

    private List<Postre> listaPostres;
    private OnPostreClickListener listener;

    // Interfaz para el clic
    public interface OnPostreClickListener {
        void onItemClick(Postre postre);
    }

    public PostreAdapter(List<Postre> listaPostres, OnPostreClickListener listener) {
        this.listaPostres = listaPostres;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new PostreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostreViewHolder holder, int position) {
        Postre postre = listaPostres.get(position);

        holder.tvNombre.setText(postre.getNombre());
        holder.tvIngredientes.setText(postre.getIngredientes());
        holder.tvPrecio.setText(postre.getPrecio() + "€");
        // holder.imgPostre.setImageResource(postre.getImagenResId());

        // Configurar el Clic
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(postre);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPostres.size();
    }

    // Clase ViewHolder interna
    public static class PostreViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPostre;
        TextView tvNombre, tvIngredientes, tvPrecio;

        public PostreViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPostre = itemView.findViewById(R.id.imgPostre);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvIngredientes = itemView.findViewById(R.id.tvIngredientes);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
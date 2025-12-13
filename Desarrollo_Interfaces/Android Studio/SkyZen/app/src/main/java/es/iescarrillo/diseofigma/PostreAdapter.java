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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new PostreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostreViewHolder holder, int position) {
        Postre postre = listaPostres.get(position);

        holder.tvNombre.setText(postre.getNombre());
        holder.tvIngredientes.setText(postre.getIngredientes());
        holder.tvPrecio.setText(postre.getPrecio() + "€");
        holder.imgPostre.setImageResource(postre.getImagen());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(postre));
    }

    @Override
    public int getItemCount() {
        return listaPostres.size();
    }

    public static class PostreViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPostre;
        TextView tvNombre, tvIngredientes, tvPrecio;

        public PostreViewHolder(@NonNull View itemView) {
            super(itemView);
            // Estos IDs coinciden con item_postre.xml
            imgPostre = itemView.findViewById(R.id.imgPostreRow);
            tvNombre = itemView.findViewById(R.id.tvNombreRow);
            tvIngredientes = itemView.findViewById(R.id.tvIngredientesRow);
            tvPrecio = itemView.findViewById(R.id.tvPrecioRow);
        }
    }
}
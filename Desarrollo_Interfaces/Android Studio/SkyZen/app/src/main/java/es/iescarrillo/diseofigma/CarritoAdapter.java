package es.iescarrillo.diseofigma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    private List<LineaPedido> listaPedidos;
    private OnAccionCarritoListener listener;

    // Interfaz para comunicar eventos al Fragment (ej: borrar)
    public interface OnAccionCarritoListener {
        void onEliminarClick(int posicion);
    }

    public CarritoAdapter(List<LineaPedido> listaPedidos, OnAccionCarritoListener listener) {
        this.listaPedidos = listaPedidos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        LineaPedido linea = listaPedidos.get(position);

        holder.tvCantidad.setText(linea.getCantidad() + "x");
        holder.tvNombre.setText(linea.getPostre().getNombre());

        // Formatear precio a 2 decimales
        holder.tvPrecio.setText(String.format("%.2f€", linea.getTotalLinea()));

        holder.btnEliminar.setOnClickListener(v -> {
            listener.onEliminarClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {
        TextView tvCantidad, tvNombre, tvPrecio;
        ImageView btnEliminar;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCantidad = itemView.findViewById(R.id.tvCantidadCarrito);
            tvNombre = itemView.findViewById(R.id.tvNombreCarrito);
            tvPrecio = itemView.findViewById(R.id.tvPrecioLinea);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
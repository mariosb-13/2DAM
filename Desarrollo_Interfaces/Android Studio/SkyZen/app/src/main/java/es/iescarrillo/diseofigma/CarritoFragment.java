package es.iescarrillo.diseofigma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarritoFragment extends Fragment {

    private RecyclerView recyclerView;
    private CarritoAdapter adapter;
    private TextView tvTotal;
    private Button btnConfirmar;
    private List<LineaPedido> listaCarrito;

    public CarritoFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        // 1. Vincular Vistas
        recyclerView = view.findViewById(R.id.rvCarrito);
        tvTotal = view.findViewById(R.id.tvTotalCarrito);
        btnConfirmar = view.findViewById(R.id.btnConfirmarPedido);

        // 2. Obtener datos del Singleton
        listaCarrito = CarritoManager.getInstance().getCesta();

        // 3. Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CarritoAdapter(listaCarrito, new CarritoAdapter.OnAccionCarritoListener() {
            @Override
            public void onEliminarClick(int posicion) {
                eliminarProducto(posicion);
            }
        });

        recyclerView.setAdapter(adapter);

        // 4. Calcular precio inicial
        actualizarPrecioTotal();

        // 5. Botón Confirmar
        btnConfirmar.setOnClickListener(v -> {
            if (listaCarrito.isEmpty()) {
                Toast.makeText(getContext(), "El carrito está vacío", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "¡Pedido realizado con éxito!", Toast.LENGTH_LONG).show();
                // Aquí podrías vaciar el carrito y navegar a otra pantalla
                CarritoManager.getInstance().vaciarCarrito();
                adapter.notifyDataSetChanged();
                actualizarPrecioTotal();
            }
        });

        return view;
    }

    // Método para borrar un item y recalcular todo
    private void eliminarProducto(int posicion) {
        listaCarrito.remove(posicion);
        adapter.notifyItemRemoved(posicion);
        // Importante: notificar cambios de rango para que no fallen los índices
        adapter.notifyItemRangeChanged(posicion, listaCarrito.size());
        actualizarPrecioTotal();
        Toast.makeText(getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
    }

    // Método auxiliar para sumar el total
    private void actualizarPrecioTotal() {
        double total = CarritoManager.getInstance().calcularTotalGeneral();
        tvTotal.setText(String.format("%.2f€", total));
    }

    // Si vuelves a esta pantalla (onResume), aseguramos que los datos estén frescos
    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            actualizarPrecioTotal();
        }
    }
}
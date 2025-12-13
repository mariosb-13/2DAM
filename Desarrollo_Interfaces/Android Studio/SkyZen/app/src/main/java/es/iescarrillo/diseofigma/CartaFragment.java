package es.iescarrillo.diseofigma;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CartaFragment extends Fragment {

    private List<Postre> misPostres;

    public CartaFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carta, container, false);

        // --- 1. DATOS ---
        misPostres = new ArrayList<>();
        misPostres.add(new Postre("Flan de Queso", "Queso Crema, Caramelo, Leche Condensada", 4.50, R.drawable.cart, false, true, true, false));
        misPostres.add(new Postre("Tarta Zanahoria", "Harina, nueces, huevo, canela", 5.00, R.drawable.cart, true, true, true, true));
        misPostres.add(new Postre("Sorbet de Limón", "Limón, azúcar, hielo, menta", 3.50, R.drawable.cart, false, false, false, false));
        misPostres.add(new Postre("Gelatina de Fresa", "Fresas, azúcar, gelatina neutra", 4.00, R.drawable.cart, false, false, false, false));
        misPostres.add(new Postre("Helado Chocolate", "Chocolate belga, nata, azúcar", 4.25, R.drawable.cart, false, true, false, false));
        misPostres.add(new Postre("Brownie", "Chocolate, nueces, harina, mantequilla", 4.80, R.drawable.cart, true, true, true, true));

        // --- 2. RECYCLERVIEW ---
        RecyclerView recyclerView = view.findViewById(R.id.rvCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PostreAdapter adapter = new PostreAdapter(misPostres, postre -> {
            int pos = misPostres.indexOf(postre);
            mostrarDialogoDetalle(pos);
        });
        recyclerView.setAdapter(adapter);


        // --- 3. BOTÓN FAB (NUEVO) ---
        FloatingActionButton fabCarrito = view.findViewById(R.id.floatingButton);
        fabCarrito.setOnClickListener(v -> {
            // Navegar al Fragment del Carrito
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new CarritoFragment()) // <--- ¡IMPORTANTE! Revisa este ID
                    .addToBackStack(null) // Para poder volver atrás con el botón del móvil
                    .commit();
        });

        return view;
    }

    // ... (Mantén aquí abajo tu método mostrarDialogoDetalle tal cual lo tenías) ...
    private void mostrarDialogoDetalle(int posicionInicial) {
        if (getContext() == null) return;

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detalle_producto);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        TextView tvNombre = dialog.findViewById(R.id.tvDialogNombre);
        TextView tvDetalle = dialog.findViewById(R.id.tvDialogDetalle);
        ImageView imgFoto = dialog.findViewById(R.id.imgDialogFoto);
        ImageView btnNavAnt = dialog.findViewById(R.id.btnNavAnt);
        ImageView btnNavSig = dialog.findViewById(R.id.btnNavSig);
        ImageView btnAtras = dialog.findViewById(R.id.btnDialogAtras);
        ImageView btnCarrito = dialog.findViewById(R.id.btnDialogCarrito);

        ImageView[] iconosAlergenos = {
                dialog.findViewById(R.id.iconGluten),
                dialog.findViewById(R.id.iconLactosa),
                dialog.findViewById(R.id.iconHuevo),
                dialog.findViewById(R.id.iconCacahuete)
        };

        final int[] posicionActual = { posicionInicial };

        Runnable actualizarUI = () -> {
            Postre p = misPostres.get(posicionActual[0]);
            tvNombre.setText(p.getNombre());
            tvDetalle.setText(p.getIngredientes().replace(", ", "\n"));
            imgFoto.setImageResource(p.getImagen());
            iconosAlergenos[0].setVisibility(p.isTieneGluten() ? View.VISIBLE : View.GONE);
            iconosAlergenos[1].setVisibility(p.isTieneLactosa() ? View.VISIBLE : View.GONE);
            iconosAlergenos[2].setVisibility(p.isTieneHuevo() ? View.VISIBLE : View.GONE);
            iconosAlergenos[3].setVisibility(p.isTieneCacahuete() ? View.VISIBLE : View.GONE);
        };

        actualizarUI.run();

        btnNavAnt.setOnClickListener(v -> {
            if (posicionActual[0] > 0) posicionActual[0]--;
            else posicionActual[0] = misPostres.size() - 1;
            actualizarUI.run();
        });

        btnNavSig.setOnClickListener(v -> {
            if (posicionActual[0] < misPostres.size() - 1) posicionActual[0]++;
            else posicionActual[0] = 0;
            actualizarUI.run();
        });

        btnAtras.setOnClickListener(v -> dialog.dismiss());

        btnCarrito.setOnClickListener(v -> {
            Postre postreSeleccionado = misPostres.get(posicionActual[0]);
            CarritoManager.getInstance().agregarProducto(postreSeleccionado);
            Toast.makeText(getContext(), "Añadido: " + postreSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }
}
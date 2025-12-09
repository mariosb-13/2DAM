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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class CartaFragment extends Fragment {

    public CartaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carta, container, false);

        List<Postre> misPostres = new ArrayList<>();
        misPostres.add(new Postre("Flan de Queso", "Queso, huevo, caramelo", 4.50, R.drawable.cart, false, true, true, false));
        misPostres.add(new Postre("Tarta Zanahoria", "Harina, nueces, huevo", 5.00, R.drawable.cart, true, true, true, true));
        misPostres.add(new Postre("Sorbet de Limón", "Limón, azúcar, hielo", 3.50, R.drawable.cart, false, false, false, false));
        misPostres.add(new Postre("Gelatina de Fresa", "Fresas, azúcar, crema", 4.00, R.drawable.cart, false, true, false, false));
        misPostres.add(new Postre("Helado de Chocolate", "Chocolate, nata, azúcar", 4.25, R.drawable.cart, false, true, false, false));
        misPostres.add(new Postre("Flan de Chocolate", "Chocolate, nata, azúcar", 4.25, R.drawable.cart, false, true, false, false));



        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvCarta);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear Adaptador con la lógica del clic
        PostreAdapter adapter = new PostreAdapter(misPostres, new PostreAdapter.OnPostreClickListener() {
            @Override
            public void onItemClick(Postre postre) {
                mostrarDialogoDetalle(postre);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    // Método para mostrar el PopUp (Dialog)
    private void mostrarDialogoDetalle(Postre postre) {
        if (getContext() == null) return;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_detalle_producto);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Vinculamos vistas básicas
        TextView tvNombre = dialog.findViewById(R.id.tvNombreDialogo);
        TextView tvDetalle = dialog.findViewById(R.id.tvDetalleDialogo);
        TextView tvPrecio = dialog.findViewById(R.id.tvPrecioDialogo);
        FloatingActionButton btnAgregar = dialog.findViewById(R.id.fabAgregar);

        ImageView imgGluten = dialog.findViewById(R.id.iconGluten);
        ImageView imgLactosa = dialog.findViewById(R.id.iconLactosa);
        ImageView imgHuevo = dialog.findViewById(R.id.iconHuevo);
        ImageView imgCacahuete = dialog.findViewById(R.id.iconCacahuete);

        // Setear datos de texto
        tvNombre.setText(postre.getNombre());
        tvDetalle.setText(postre.getIngredientes().replace(", ", "\n"));
        tvPrecio.setText(postre.getPrecio() + "€");


        imgGluten.setVisibility(postre.isTieneGluten() ? View.VISIBLE : View.GONE);
        imgLactosa.setVisibility(postre.isTieneLactosa() ? View.VISIBLE : View.GONE);
        imgHuevo.setVisibility(postre.isTieneHuevo() ? View.VISIBLE : View.GONE);
        imgCacahuete.setVisibility(postre.isTieneCacahuete() ? View.VISIBLE : View.GONE);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
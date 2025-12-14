package es.iescarrillo.diseofigma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class IdiomaFragment extends Fragment {

    public IdiomaFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idioma, container, false);

        // --- BOTÓN GUARDAR (NUEVO) ---
        Button btnGuardar = view.findViewById(R.id.btnGuardarIdioma);
        btnGuardar.setOnClickListener(v -> {
            // 1. Mostrar mensaje de confirmación
            Toast.makeText(getContext(), "Idioma guardado correctamente", Toast.LENGTH_SHORT).show();

            // 2. Volver a la pantalla anterior
            getParentFragmentManager().popBackStack();
        });

        // Listeners de los idiomas (Solo visual por ahora)
        TextView tvSpanish = view.findViewById(R.id.langSpanish);
        tvSpanish.setOnClickListener(v -> Toast.makeText(getContext(), "Seleccionado: Español", Toast.LENGTH_SHORT).show());

        TextView tvEnglish = view.findViewById(R.id.langEnglish);
        tvEnglish.setOnClickListener(v -> Toast.makeText(getContext(), "Selected: English", Toast.LENGTH_SHORT).show());

        return view;
    }
}
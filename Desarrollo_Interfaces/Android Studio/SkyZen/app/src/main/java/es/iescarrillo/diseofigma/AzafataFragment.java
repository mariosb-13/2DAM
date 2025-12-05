package es.iescarrillo.diseofigma;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class AzafataFragment extends Fragment {

    // Colores para el estado de los botones
    private final int COLOR_ACTIVE_BG = Color.parseColor("#005a8d"); // Azul oscuro
    private final int COLOR_ACTIVE_TEXT = Color.WHITE;
    private final int COLOR_INACTIVE_TEXT = Color.parseColor("#333333");
    private final int COLOR_INACTIVE_STROKE = Color.parseColor("#0073B1");

    public AzafataFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Asegúrate de que el nombre del layout coincida con tu archivo XML
        return inflater.inflate(R.layout.fragment_azafata, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Configurar botones de servicio
        setupServiceButton(view.findViewById(R.id.btnMedical), "Atención médica");
        setupServiceButton(view.findViewById(R.id.btnDiscomfort), "Malestar");
        setupServiceButton(view.findViewById(R.id.btnFood), "Comida");
        setupServiceButton(view.findViewById(R.id.btnCleaning), "Limpieza y orden");
        setupServiceButton(view.findViewById(R.id.btnInfo), "Información del vuelo");
        setupServiceButton(view.findViewById(R.id.btnOther), "Otro");
    }

    /**
     * Configura el comportamiento de cada botón:
     * - Click -> Cambia visualmente -> Muestra Popup
     */
    private void setupServiceButton(MaterialButton button, String serviceName) {
        button.setOnClickListener(v -> {

            // Verificar si el botón ya estaba activo para alternar (toggle) o no.
            // En este caso, asumimos que si clickas, lo activas.
            boolean isChecked = !button.isChecked();

            if (isChecked) {
                // CAMBIO VISUAL: ESTADO "PULSADO"
                setButtonState(button, true);

                // MOSTRAR POPUP
                showConfirmationDialog(button, serviceName);
            } else {
                // Si ya estaba pulsado y lo tocan de nuevo, lo desactivamos
                setButtonState(button, false);
            }
        });
    }

    /**
     * Muestra el Popup de confirmación
     */
    private void showConfirmationDialog(MaterialButton button, String serviceName) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Solicitud de Asistencia")
                .setMessage("¿Desea solicitar " + serviceName + " a la tripulación?")
                .setCancelable(false) // Evita que se cierre tocando fuera
                .setIcon(R.drawable.ic_launcher_foreground)

                .setPositiveButton("CONFIRMAR", (dialog, which) -> {
                    // Acción confirmada: El botón se queda pulsado.
                    // Aquí enviarías la petición al servidor/azafata
                    Toast.makeText(getContext(), "Solicitud enviada: " + serviceName, Toast.LENGTH_SHORT).show();
                })

                .setNegativeButton("CANCELAR", (dialog, which) -> {
                    // Acción cancelada: Revertimos el botón a su estado normal
                    setButtonState(button, false);
                    Toast.makeText(getContext(), "Solicitud cancelada", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    /**
     * Cambia la apariencia del botón manualmente
     */
    private void setButtonState(MaterialButton button, boolean isActive) {
        button.setChecked(isActive); // Guarda el estado lógico

        if (isActive) {
            button.setBackgroundColor(COLOR_ACTIVE_BG);
            button.setTextColor(COLOR_ACTIVE_TEXT);
        } else {
            button.setBackgroundColor(Color.TRANSPARENT);
            button.setTextColor(COLOR_INACTIVE_TEXT);
            button.setStrokeColor(android.content.res.ColorStateList.valueOf(COLOR_INACTIVE_STROKE));
        }
    }
}
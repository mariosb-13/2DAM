package  com.example.tareaevolutiva;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton; // Importante: es ImageButton, no Button
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
// Importación del SideSheet de Material 3
import com.google.android.material.sidesheet.SideSheetDialog;
import android.widget.Button; // Para los botones de dentro del sheet

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asegúrate de que este nombre coincide con tu archivo XML principal
        setContentView(R.layout.activity_main);

        // REFERENCIAR EL BOTÓN DEL PERFIL
        // En tu XML se llama "imgProfile" y es un ImageButton
        ImageButton btnPerfil = findViewById(R.id.imgProfile);

        // DARLE LA FUNCIONALIDAD
        btnPerfil.setOnClickListener(v -> abrirMenuLateral());
    }

    /**
     * Método que abre el menu lateral
     */
    private void abrirMenuLateral() {
        // Crea la instancia de sideSheet
        SideSheetDialog sideSheet = new SideSheetDialog(this);

        // Asignarle el diseño XML del menú
        sideSheet.setContentView(R.layout.layout_side_sheet);

        Button btnLogout = sideSheet.findViewById(R.id.btnCerrarSesion);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();
                sideSheet.dismiss();
            });
        }

        // Mostrar el menú
        sideSheet.show();
    }
}
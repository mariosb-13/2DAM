package  com.example.tareaevolutiva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.sidesheet.SideSheetDialog;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCIAR EL BOTÓN DEL PERFIL
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

        //Funcionalidad al boton de cerrar sesion
        Button btnLogout = sideSheet.findViewById(R.id.btnCerrarSesion);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        }
        // Mostrar el menú
        sideSheet.show();
    }
}
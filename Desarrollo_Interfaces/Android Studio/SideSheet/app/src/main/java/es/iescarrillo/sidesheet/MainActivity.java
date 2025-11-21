package es.iescarrillo.sidesheet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
// Importante: Importar la clase del SideSheet
import com.google.android.material.sidesheet.SideSheetDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVerDetalles = findViewById(R.id.btnVerDetalles);

        btnVerDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSideSheet();
            }
        });
    }

    private void mostrarSideSheet() {
        // 1. Instanciar el SideSheetDialog pasando el contexto (this)
        SideSheetDialog sideSheetDialog = new SideSheetDialog(this);

        // 2. Asignarle el layout XML que creaste antes
        sideSheetDialog.setContentView(R.layout.layout_outfit_detail_side_sheet);

        // 3. Para interactuar con los elementos DENTRO del Sheet,
        // debes usar sideSheetDialog.findViewById (NO findViewById a secas)
        Button btnCerrar = sideSheetDialog.findViewById(R.id.btnCloseSheet);
        TextView txtTitulo = sideSheetDialog.findViewById(R.id.tvTitulo); // Ejemplo

        // Configurar lógica interna del Sheet
        if (btnCerrar != null) {
            btnCerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sideSheetDialog.dismiss(); // Cierra la hoja
                }
            });
        }

        // 4. Mostrar el Side Sheet
        sideSheetDialog.show();
    }
}
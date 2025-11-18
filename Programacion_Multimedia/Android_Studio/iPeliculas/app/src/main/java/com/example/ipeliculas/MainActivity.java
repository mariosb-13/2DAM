package com.example.ipeliculas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    /**
     * 1. INFLAR EL MENÚ
     * Este método se llama una vez para crear el menú en la barra de acción.
     * Carga el XML del menú (res/menu/main_menu.xml) en la interfaz.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Carga el archivo main_menu.xml en el objeto Menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true; // Devuelve 'true' para que el menú se muestre.
    }

    /**
     * 2. MANEJAR CLICS
     * Este método se llama cada vez que el usuario selecciona un ítem del menú.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Obtenemos el ID del ítem que ha sido pulsado
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Toast.makeText(this, "Acción de Búsqueda seleccionada", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "Acción de Ajustes seleccionada", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_profile) {
            Toast.makeText(this, "Acción de Perfil seleccionada", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Si el ítem no fue manejado por nuestro código, dejamos que el sistema lo maneje.
        return super.onOptionsItemSelected(item);
    }
}
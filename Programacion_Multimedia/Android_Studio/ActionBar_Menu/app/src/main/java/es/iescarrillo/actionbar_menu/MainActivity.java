package es.iescarrillo.actionbar_menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
     * Este método es necesario para incluir el menú de la activity
     *
     * @param menu The options menu in which you place your items.
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.MenuBuscar) {
            Toast.makeText(this, "Menu Buscar", Toast.LENGTH_SHORT).show();

            // Creamos un Intent para abrir el navegador
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            startActivity(intent); // Lanzamos la actividad
            return true;
        } else if (item.getItemId() == R.id.MenuPrueba) {
            Toast.makeText(this, "Menu Prueba", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.MenuAcerca) {
            Toast.makeText(this, "Menu Acerca de", Toast.LENGTH_SHORT).show();
            // Creamos un Intent para abrir el navegador
            Intent intent = new Intent(this, AcercaDe.class);
            startActivity(intent);
            return true;
        } else {
            Toast.makeText(this, "No has seleccionado nada", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }
    }
}
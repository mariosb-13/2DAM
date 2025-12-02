package es.iescarrillo.diseofigma;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// Importa aquí tus otros fragments cuando los crees
// import es.iescarrillo.diseofigma.AzafataFragment;
// import es.iescarrillo.diseofigma.VueloFragment;
// import es.iescarrillo.diseofigma.CartaFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 1. Cargar el Fragment inicial por defecto (MainFragment)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainFragment())
                    .commit();
        }

        // 2. Configurar el Listener para cambiar de Fragment
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_volver) {
                selectedFragment = new MainFragment();
            }
            else if (itemId == R.id.nav_azafata) {
                // selectedFragment = new AzafataFragment(); // Descomenta al crear la clase
                selectedFragment = new MainFragment(); // (Temporal hasta que crees la clase)
            }
            else if (itemId == R.id.nav_info_vuelo) {
                selectedFragment = new VueloFragment();
            }
            else if (itemId == R.id.nav_carta) {
                // selectedFragment = new CartaFragment();   // Descomenta al crear la clase
                selectedFragment = new MainFragment(); // (Temporal)
            }

            // Realizar el intercambio (Transacción)
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
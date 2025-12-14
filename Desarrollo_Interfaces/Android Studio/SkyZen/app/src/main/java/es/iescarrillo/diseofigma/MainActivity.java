package es.iescarrillo.diseofigma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.MaterialToolbar; // <--- IMPORTANTE
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainFragment())
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_volver) {
                selectedFragment = new MainFragment();
            }
            else if (itemId == R.id.nav_azafata) {
                selectedFragment = new AzafataFragment();
            }
            else if (itemId == R.id.nav_info_vuelo) {
                selectedFragment = new VueloFragment();
            }
            else if (itemId == R.id.nav_carta) {
                selectedFragment = new CartaFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.language_selection) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new IdiomaFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        if (id == R.id.logOut) {
            // 1. Crear el Intent para ir al Login
            Intent intent = new Intent(this, LoginActivity.class);
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

            // 2. Limpiar el historial (flags) para que no se pueda volver atrás
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // 3. Iniciar la actividad y cerrar la actual
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
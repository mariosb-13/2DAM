package com.example.tareaevolutiva;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Cargar el layout que contiene el FrameLayout y la BottomNavigationView
        setContentView(R.layout.activity_main_container);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Cargar el Fragment inicial (MainFragment) al arrancar
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commit();
            // Asegura que el ítem de inicio esté seleccionado
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }

        // Configurar el Listener para cambiar de Fragment al presionar un ítem
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        int itemId = item.getItemId();

        if (itemId == R.id.nav_home) {
            selectedFragment = MainFragment.newInstance();
        } else if (itemId == R.id.nav_favorites) {
            selectedFragment = FavoriteFragment.newInstance("","");
        } else if (itemId == R.id.nav_calendar) {
            selectedFragment = CalendarFragment.newInstance("","");
        } else {
            return false;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }
}
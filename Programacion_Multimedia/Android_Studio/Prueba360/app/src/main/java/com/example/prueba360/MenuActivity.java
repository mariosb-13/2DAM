package com.example.prueba360;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        AutoCompleteTextView searchView = findViewById(R.id.autoCompleteSearch);
        String[] sneakers = getResources().getStringArray(R.array.sneaker_slugs);

        // --- CAMBIO AQUÍ: Usamos R.layout.item_busqueda en lugar del de Android ---
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_busqueda,
                sneakers
        );

        searchView.setAdapter(adapter);

        // --- TRUCO: Mostrar lista al hacer clic (aunque no hayas escrito) ---
        searchView.setOnClickListener(v -> searchView.showDropDown());
        searchView.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) searchView.showDropDown();
        });

        searchView.setOnItemClickListener((parent, view, position, id) -> {
            String slugSeleccionado = (String) parent.getItemAtPosition(position);
            abrirVisor(slugSeleccionado);
            searchView.setText("");
            // Ocultar teclado (opcional para limpiar la vista)
            // searchView.clearFocus();
        });
    }

    private void abrirVisor(String slug) {
        // Un pequeño log visual para confirmar que cogió el clic
        Toast.makeText(this, "Abriendo: " + slug, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("SLUG_ZAPATILLA", slug);
        startActivity(intent);
    }
}
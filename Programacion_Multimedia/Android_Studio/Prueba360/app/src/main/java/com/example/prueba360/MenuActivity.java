package com.example.prueba360;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnChicago = findViewById(R.id.btnChicago);
        Button btnPanda = findViewById(R.id.btnPanda);
        Button btnTravis = findViewById(R.id.btnTravis);

        // Botón 1: Jordan Chicago
        btnChicago.setOnClickListener(v ->
                abrirVisor("Air-Jordan-1-Retro-High-OG-Chicago-Reimagined"));

        // Botón 2: Panda
        btnPanda.setOnClickListener(v ->
                abrirVisor("Nike-Dunk-Low-Retro-White-Black-2021"));

        // Botón 3: Travis Scott
        btnTravis.setOnClickListener(v ->
                abrirVisor("Air-Jordan-1-Retro-Low-OG-SP-Travis-Scott-Reverse-Mocha"));
    }

    private void abrirVisor(String slug) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("SLUG_ZAPATILLA", slug);
        startActivity(intent);
    }
}
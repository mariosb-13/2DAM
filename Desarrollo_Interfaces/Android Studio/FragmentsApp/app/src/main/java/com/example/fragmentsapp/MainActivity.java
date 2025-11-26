package com.example.fragmentsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/*
    Carga ProductListFragment al iniciar la app
    savedInstanceState == null evita recrear fragmentos al rotar la pantalla
    replace() muestra el fragment inicial
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ProductListFragment())
                    .commit();
        }
    }
}
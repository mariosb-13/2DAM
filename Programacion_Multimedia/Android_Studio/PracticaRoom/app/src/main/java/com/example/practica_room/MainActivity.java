package com.example.practica_room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos los botones
    Button btnCrearUsuario, btnListarUsuarios;
    Button btnCrearCategoria, btnListarCategorias;
    Button btnCrearTarea, btnListarTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        // Vincular botones con la vista (XML)
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario);
        btnListarUsuarios = findViewById(R.id.btnListarUsuarios);

        btnCrearCategoria = findViewById(R.id.btnCrearCategoria);
        btnListarCategorias = findViewById(R.id.btnListarCategorias);

        btnCrearTarea = findViewById(R.id.btnCrearTarea);
        btnListarTareas = findViewById(R.id.btnListarTareas);


        // --- GESTIÓN DE USUARIOS ---
        btnCrearUsuario.setOnClickListener(v -> {
            // Navegar a la pantalla de Insertar Usuario
            Intent intent = new Intent(MainActivity.this, UsuarioActivity.class);
            startActivity(intent);
        });

        btnListarUsuarios.setOnClickListener(v -> {
            // Navegar a la lista de usuarios
            Intent intent = new Intent(MainActivity.this, ListaUsuariosActivity.class);
            startActivity(intent);
        });

        // --- GESTIÓN DE CATEGORÍAS ---
        btnCrearCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriaActivity.class);
            startActivity(intent);
        });

        btnListarCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaCategoriasActivity.class);
            startActivity(intent);
        });

        // --- GESTIÓN DE TAREAS ---
        btnCrearTarea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
            startActivity(intent);
        });

        btnListarTareas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaTareasActivity.class);
            startActivity(intent);
        });
    }
}
package com.example.practica_room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_room.AppDatabase;
import com.example.practica_room.entities.Categoria;
import com.example.practica_room.entities.Tarea;
import com.example.practica_room.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class NuevaTareaActivity extends AppCompatActivity {

    EditText etTitulo, etDesc;
    Spinner spinnerUsuario, spinnerCategoria;
    Button btnGuardar;

    // Listas para manejar los datos recuperados de la BD
    List<Usuario> listaUsuarios;
    List<Categoria> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        etTitulo = findViewById(R.id.etTituloTarea);
        etDesc = findViewById(R.id.etDescTarea);
        spinnerUsuario = findViewById(R.id.spinnerUsuario);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnGuardar = findViewById(R.id.btnGuardarTarea);

        cargarSpinners();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTarea();
            }
        });
    }

    private void cargarSpinners() {
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        // 1. Obtener listas de la BD
        listaUsuarios = db.daoUsuario().obtenerUsuarios();
        listaCategorias = db.daoCategoria().obtenerCategorias();

        // 2. Crear listas de Strings solo con los nombres para mostrarlos en el Spinner
        List<String> nombresUsuarios = new ArrayList<>();
        for (Usuario u : listaUsuarios) {
            nombresUsuarios.add(u.nombre);
        }

        List<String> nombresCategorias = new ArrayList<>();
        for (Categoria c : listaCategorias) {
            nombresCategorias.add(c.nombre);
        }

        // 3. Configurar los adaptadores para los Spinners
        // Si no hay usuarios o categorías, avisamos
        if (listaUsuarios.isEmpty() || listaCategorias.isEmpty()) {
            Toast.makeText(this, "Primero crea Usuarios y Categorías", Toast.LENGTH_LONG).show();
            btnGuardar.setEnabled(false); // Desactivar botón para evitar errores
            return;
        }

        ArrayAdapter<String> adapterUser = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombresUsuarios);
        spinnerUsuario.setAdapter(adapterUser);

        ArrayAdapter<String> adapterCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombresCategorias);
        spinnerCategoria.setAdapter(adapterCat);
    }

    private void guardarTarea() {
        String titulo = etTitulo.getText().toString();
        String descripcion = etDesc.getText().toString();

        if (titulo.isEmpty()) {
            Toast.makeText(this, "Ponle un título a la tarea", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. Averiguar qué Usuario y Categoría se han seleccionado
        int posicionUsuario = spinnerUsuario.getSelectedItemPosition();
        int posicionCategoria = spinnerCategoria.getSelectedItemPosition();

        // Recuperamos el objeto real usando la posición de la lista
        Usuario usuarioSeleccionado = listaUsuarios.get(posicionUsuario);
        Categoria categoriaSeleccionada = listaCategorias.get(posicionCategoria);

        // 2. Crear la Tarea
        Tarea tarea = new Tarea();
        tarea.titulo = titulo;
        tarea.descripcion = descripcion;
        tarea.idUsuario = usuarioSeleccionado.idUsuario;     // RELACIÓN: Aquí guardamos la FK
        tarea.idCategoria = categoriaSeleccionada.idCategoria; // RELACIÓN: Aquí guardamos la FK
        tarea.completada = false; // Por defecto

        // 3. Guardar en BD
        AppDatabase.getDatabase(getApplicationContext()).daoTarea().insertarTarea(tarea);

        Toast.makeText(this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
package com.example.practica_room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_room.entities.Categoria;

public class CategoriaActivity extends AppCompatActivity {

    EditText etNombre, etDesc;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        etNombre = findViewById(R.id.etNombreCat);
        etDesc = findViewById(R.id.etDescCat);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String desc = etDesc.getText().toString();

            if (nombre.isEmpty()) {
                Toast.makeText(CategoriaActivity.this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
                return;
            }

            Categoria categoria = new Categoria();
            categoria.nombre = nombre;
            categoria.descripcion = desc;

            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            db.daoCategoria().insertarCategoria(categoria);

            Toast.makeText(CategoriaActivity.this, "Categoría guardada", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
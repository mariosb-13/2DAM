package com.example.practica_room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_room.entities.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    EditText etNombre, etEmail;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Obtener datos
                String nombre = etNombre.getText().toString();
                String email = etEmail.getText().toString();

                if (nombre.isEmpty() || email.isEmpty()) {
                    Toast.makeText(UsuarioActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. Crear objeto
                Usuario usuario = new Usuario();
                usuario.nombre = nombre;
                usuario.email = email;

                // 3. Guardar en BBDD
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                db.daoUsuario().insertarUsuario(usuario);

                Toast.makeText(UsuarioActivity.this, "Usuario guardado", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad y vuelve al menú
            }
        });
    }
}
package com.example.practica_room;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_room.entities.Usuario;

import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity {

    TextView tvListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        tvListado = findViewById(R.id.tvListado);

        // Obtener la BBDD
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        // Consultar datos
        List<Usuario> lista = db.daoUsuario().obtenerUsuarios();

        // Mostrar en pantalla (Estilo de tus apuntes)
        StringBuilder texto = new StringBuilder();
        for (Usuario u : lista) {
            texto.append("ID: ").append(u.idUsuario)
                    .append("\nNombre: ").append(u.nombre)
                    .append("\nEmail: ").append(u.email)
                    .append("\n-----------------\n");
        }

        if (lista.isEmpty()) {
            tvListado.setText("No hay usuarios registrados.");
        } else {
            tvListado.setText(texto.toString());
        }
    }
}
package com.example.practica_room;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_room.entities.Tarea;

import java.util.List;

public class ListaTareasActivity extends AppCompatActivity {

    TextView tvListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        tvListado = findViewById(R.id.tvListadoTareas);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        List<Tarea> lista = db.daoTarea().obtenerTodasLasTareas();

        StringBuilder texto = new StringBuilder();
        for (Tarea t : lista) {
            texto.append("ID: ").append(t.idTarea)
                    .append("\nTítulo: ").append(t.titulo)
                    // Aquí mostramos la relación (Foreign Keys)
                    .append("\nAsignada a Usuario ID: ").append(t.idUsuario)
                    .append("\nCategoría ID: ").append(t.idCategoria)
                    .append("\n-----------------\n");
        }

        if (lista.isEmpty()) {
            tvListado.setText("No hay tareas registradas.");
        } else {
            tvListado.setText(texto.toString());
        }
    }
}
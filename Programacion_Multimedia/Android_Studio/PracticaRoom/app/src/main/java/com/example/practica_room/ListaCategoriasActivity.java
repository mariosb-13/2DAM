package com.example.practica_room;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_room.entities.Categoria;

import java.util.List;

public class ListaCategoriasActivity extends AppCompatActivity {

    TextView tvListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        tvListado = findViewById(R.id.tvListadoCat);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        List<Categoria> lista = db.daoCategoria().obtenerCategorias();

        StringBuilder texto = new StringBuilder();
        for (Categoria c : lista) {
            texto.append("ID: ").append(c.idCategoria)
                    .append(" - ").append(c.nombre)
                    .append("\nDesc: ").append(c.descripcion)
                    .append("\n-----------------\n");
        }

        tvListado.setText(texto.toString());
    }
}
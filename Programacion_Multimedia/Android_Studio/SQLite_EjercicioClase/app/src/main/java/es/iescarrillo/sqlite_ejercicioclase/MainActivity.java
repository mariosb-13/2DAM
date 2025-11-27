package es.iescarrillo.sqlite_ejercicioclase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.*;

import es.iescarrillo.sqlite_ejercicioclase.dao.TareaDAO;
import es.iescarrillo.sqlite_ejercicioclase.dao.UsuarioDAO;
import es.iescarrillo.sqlite_ejercicioclase.entidades.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etId, etNombre, etCorreo;
    Button btnInsertar, btnMostrar, btnActualizar, btnEliminar, btnTarea;
    TextView tvResultado;

    UsuarioDAO usuarioDAO;
    TareaDAO tareaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioDAO = new UsuarioDAO(this);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnTarea = findViewById(R.id.btnTarea);
        btnEliminar = findViewById(R.id.btnEliminar);
        tvResultado = findViewById(R.id.tvResultado);

        btnInsertar.setOnClickListener(v -> {
            Usuario u = new Usuario(
                    etNombre.getText().toString(),
                    etCorreo.getText().toString()
            );
            usuarioDAO.insertar(u);
            Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show();
        });

        btnMostrar.setOnClickListener(v -> {
            ArrayList<Usuario> lista = usuarioDAO.obtenerTodos();
            StringBuilder sb = new StringBuilder();
            for (Usuario u : lista) {
                sb.append(u.getId()).append(" | ")
                        .append(u.getNombre()).append(" | ")
                        .append(u.getCorreo()).append("\n");
            }

            tvResultado.setText(sb.toString());
        });

        btnActualizar.setOnClickListener(v -> {
            Usuario u = new Usuario(
                    Integer.parseInt(etId.getText().toString()),
                    etNombre.getText().toString(),
                    etCorreo.getText().toString()
            );
            usuarioDAO.actualizar(u);
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
        });

        btnEliminar.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            usuarioDAO.eliminar(id);
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
        });

        btnTarea.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityTarea.class);
            startActivity(intent);
        });
    }

}

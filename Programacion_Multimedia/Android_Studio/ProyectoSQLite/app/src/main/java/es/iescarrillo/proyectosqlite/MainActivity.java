package es.iescarrillo.proyectosqlite;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.adapters.CocheAdapter;
import es.iescarrillo.proyectosqlite.dao.CocheDAO;
import es.iescarrillo.proyectosqlite.dao.MarcaDAO;
import es.iescarrillo.proyectosqlite.dao.MotorDAO;
import es.iescarrillo.proyectosqlite.dao.ProveedorDAO;
import es.iescarrillo.proyectosqlite.database.DatabaseHelper;
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CocheAdapter adapter;
    private ArrayList<Coche> listaCoches;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajuste EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerCoches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar DBHelper y DAOs
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        CocheDAO cocheDAO = new CocheDAO(dbHelper);
        MarcaDAO marcaDAO = new MarcaDAO(dbHelper);
        ProveedorDAO proveedorDAO = new ProveedorDAO(dbHelper);
        MotorDAO motorDAO = new MotorDAO(dbHelper);

        // Cargar datos
        listaCoches = cocheDAO.obtenerCoches();

        // Inicializar Adapter con Spinners
        adapter = new CocheAdapter(listaCoches);
        recyclerView.setAdapter(adapter);
    }
}

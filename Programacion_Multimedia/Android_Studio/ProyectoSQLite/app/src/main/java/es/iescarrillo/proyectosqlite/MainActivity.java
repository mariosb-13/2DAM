package es.iescarrillo.proyectosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
import es.iescarrillo.proyectosqlite.database.DatabaseHelper;
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CocheAdapter adapter;
    private ArrayList<Coche> listaCoches;

    private CocheDAO cocheDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerCoches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        cocheDAO = new CocheDAO(dbHelper);

    }


    /**
     * Este método se llama cuando la actividad vuelve a estar en primer plano. Esto se hace para que la bbdd se recargue
     */
    @Override
    protected void onResume() {
        super.onResume();

        listaCoches = cocheDAO.obtenerCoches();

        adapter = new CocheAdapter(listaCoches);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Método que infla el menú top de la toolbar
     * @param menu The options menu in which you place your items.
     *
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top, menu);
        return true;
    }

    /**
     * Método que se llama cuando se selecciona un elemento del menú top
     * @param item The menu item that was selected.
     *
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_car) {
            Intent intent = new Intent(this, AddCocheActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
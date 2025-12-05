package es.iescarrillo.proyectosqlite;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.dao.CocheDAO;
import es.iescarrillo.proyectosqlite.dao.MarcaDAO;
import es.iescarrillo.proyectosqlite.dao.MotorDAO;
import es.iescarrillo.proyectosqlite.dao.ProveedorDAO;
import es.iescarrillo.proyectosqlite.database.DatabaseHelper;
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class AddCocheActivity extends AppCompatActivity {

    private EditText etMatricula, etModelo, etPrecio;
    private Spinner spMarca, spProveedor, spMotor;
    private Button btnCrear;

    private DatabaseHelper dbHelper;
    private MarcaDAO marcaDAO;
    private ProveedorDAO proveedorDAO;
    private MotorDAO motorDAO;
    private CocheDAO cocheDAO;

    private ArrayList<String> listaMarcas, listaProveedores, listaMotores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_coche);

        // --- 1. CONFIGURACIÓN DE TOOLBAR (Importante para que se vea la barra) ---
        // Asegúrate de tener <com.google.android.material.appbar.MaterialToolbar android:id="@+id/toolbar" ... /> en tu XML
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Botón atrás
                getSupportActionBar().setTitle("Añadir Coche");
            }
        }

        // Configuración de márgenes para EdgeToEdge
        // NOTA: Asegúrate de que el ID "main" existe en tu activity_add_coche.xml
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- 2. INICIALIZAR BBDD Y DAOS ---
        dbHelper = new DatabaseHelper(this);
        marcaDAO = new MarcaDAO(dbHelper);
        proveedorDAO = new ProveedorDAO(dbHelper);
        motorDAO = new MotorDAO(dbHelper);
        cocheDAO = new CocheDAO(dbHelper);

        // --- 3. VINCULAR VISTAS ---
        // Asegúrate de que estos IDs coincidan con tu activity_add_coche.xml
        etMatricula = findViewById(R.id.etMatriculaAdd);
        etModelo = findViewById(R.id.etModeloAdd);
        etPrecio = findViewById(R.id.etPrecioAdd);
        spMarca = findViewById(R.id.spinnerMarcaAdd);
        spProveedor = findViewById(R.id.spinnerProveedorAdd);
        spMotor = findViewById(R.id.spinnerMotorAdd);
        btnCrear = findViewById(R.id.btnCrearCoche);

        // --- 4. CARGAR DATOS EN SPINNERS ---
        cargarListasDesdeBD();
        configurarSpinner(spMarca, listaMarcas);
        configurarSpinner(spProveedor, listaProveedores);
        configurarSpinner(spMotor, listaMotores);

        // --- 5. LISTENER DEL BOTÓN ---
        btnCrear.setOnClickListener(v -> crearCoche());
    }

    private void cargarListasDesdeBD() {
        listaMarcas = marcaDAO.obtenerNombresMarcas();
        listaProveedores = proveedorDAO.obtenerNombresProveedores();
        listaMotores = motorDAO.obtenerNombresMotores();
    }

    private void configurarSpinner(Spinner spinner, ArrayList<String> datos) {
        if (datos != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, datos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    private void crearCoche() {
        // Validaciones básicas
        String matricula = etMatricula.getText().toString().trim();
        String modelo = etModelo.getText().toString().trim();
        String precioStr = etPrecio.getText().toString().trim();

        if (matricula.isEmpty() || modelo.isEmpty() || precioStr.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El precio no es válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear objeto Coche
        Coche nuevoCoche = getCoche(matricula, modelo, precio);

        // Insertar en Base de Datos
        // Asumo que tu CocheDAO tiene un método insertarCoche(Coche c)
        cocheDAO.insertarCoche(nuevoCoche);

        Toast.makeText(this, "Coche añadido correctamente", Toast.LENGTH_SHORT).show();
        finish(); // Cierra la actividad y vuelve a la lista
    }

    @NonNull
    private Coche getCoche(String matricula, String modelo, double precio) {
        Coche nuevoCoche = new Coche();
        nuevoCoche.setMatricula(matricula);
        nuevoCoche.setModelo(modelo);
        nuevoCoche.setPrecio_venta(precio);

        nuevoCoche.setId_Marca(spMarca.getSelectedItemPosition() + 1);
        nuevoCoche.setId_Proveedor(spProveedor.getSelectedItemPosition() + 1);
        nuevoCoche.setId_Motor(spMotor.getSelectedItemPosition() + 1);
        return nuevoCoche;
    }
    
}
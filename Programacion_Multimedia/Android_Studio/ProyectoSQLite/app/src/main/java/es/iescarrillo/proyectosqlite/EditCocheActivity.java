package es.iescarrillo.proyectosqlite;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.dao.CocheDAO;
import es.iescarrillo.proyectosqlite.dao.MarcaDAO;
import es.iescarrillo.proyectosqlite.dao.MotorDAO;
import es.iescarrillo.proyectosqlite.dao.ProveedorDAO;
import es.iescarrillo.proyectosqlite.database.DatabaseHelper;
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class EditCocheActivity extends AppCompatActivity {

    private EditText etMatricula, etModelo, etPrecio;
    private Spinner spMarca, spProveedor, spMotor;
    private Button btnGuardar;
    private Coche cocheActual;

    private DatabaseHelper dbHelper;
    private MarcaDAO marcaDAO;
    private ProveedorDAO proveedorDAO;
    private MotorDAO motorDAO;
    private CocheDAO cocheDAO;

    private ArrayList<String> listaMarcas, listaProveedores, listaMotores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_coche);

        // Inicializamos BBDD y DAOs
        dbHelper = new DatabaseHelper(this);
        marcaDAO = new MarcaDAO(dbHelper);
        proveedorDAO = new ProveedorDAO(dbHelper);
        motorDAO = new MotorDAO(dbHelper);
        cocheDAO = new CocheDAO(dbHelper);

        // Inicializar Vistas
        etMatricula = findViewById(R.id.etMatriculaAdd);
        etModelo = findViewById(R.id.etModeloAdd);
        etPrecio = findViewById(R.id.etPrecioAdd);
        spMarca = findViewById(R.id.spinnerMarcaAdd);
        spProveedor = findViewById(R.id.spinnerProveedorAdd);
        spMotor = findViewById(R.id.spinnerMotorEditar);
        btnGuardar = findViewById(R.id.btnCrearCoche);

        // Obtiene el Coche
        if (getIntent().hasExtra("coche_objeto")) {
            cocheActual = (Coche) getIntent().getSerializableExtra("coche_objeto");
        } else {
            Toast.makeText(this, "Error al cargar el coche", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Cargar los campos de texto con los datos del coche
        etMatricula.setText(cocheActual.getMatricula());
        etModelo.setText(cocheActual.getModelo());
        etPrecio.setText(String.valueOf(cocheActual.getPrecio_venta()));

        cargarListasDesdeBD();

        configurarSpinner(spMarca, listaMarcas);
        configurarSpinner(spProveedor, listaProveedores);
        configurarSpinner(spMotor, listaMotores);

        // Buscamos el nombre en la BD usando el ID, y luego buscamos su posición en la lista
        seleccionarValorActual();

        // Botón Guardar
        btnGuardar.setOnClickListener(v -> guardarCambios());
    }

    private void cargarListasDesdeBD() {
        listaMarcas = marcaDAO.obtenerNombresMarcas();
        listaProveedores = proveedorDAO.obtenerNombresProveedores();
        listaMotores = motorDAO.obtenerNombresMotores();
    }

    /**
     * Método que configura los spinners
     *
     * @param spinner
     * @param datos
     */
    private void configurarSpinner(Spinner spinner, ArrayList<String> datos) {
        if (datos != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, datos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    /**
     * Método que selecciona el valor actual de los spinners
     */
    private void seleccionarValorActual() {
        // MARCA
        String nombreMarca = marcaDAO.obtenerNombreMarca(cocheActual.getId_Marca());
        if (nombreMarca != null) {
            // Buscamos en qué posición de la lista está "Toyota"
            int posicion = listaMarcas.indexOf(nombreMarca);
            if (posicion >= 0) spMarca.setSelection(posicion);
        }

        // PROVEEDOR
        String nombreProv = proveedorDAO.obtenerNombreProveedor(cocheActual.getId_Proveedor());
        if (nombreProv != null) {
            int posicion = listaProveedores.indexOf(nombreProv);
            if (posicion >= 0) spProveedor.setSelection(posicion);
        }

        // MOTOR
        String nombreMotor = motorDAO.obtenerNombreMotor(cocheActual.getId_Motor());
        if (nombreMotor != null) {
            int posicion = listaMotores.indexOf(nombreMotor);
            if (posicion >= 0) spMotor.setSelection(posicion);
        }
    }

    private void guardarCambios() {
        cocheActual.setModelo(etModelo.getText().toString());
        try {
            cocheActual.setPrecio_venta(Double.parseDouble(etPrecio.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        cocheActual.setId_Marca(spMarca.getSelectedItemPosition() + 1);
        cocheActual.setId_Proveedor(spProveedor.getSelectedItemPosition() + 1);
        cocheActual.setId_Motor(spMotor.getSelectedItemPosition() + 1);

        cocheDAO.actualizarCoche(cocheActual);
        Toast.makeText(this, "Coche actualizado", Toast.LENGTH_SHORT).show();
        finish();
    }
}
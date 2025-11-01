package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PendingProduct extends AppCompatActivity {

    Button btnSave, btnCancel;
    Spinner spinnerProductsPending;
    static ArrayList<Producto> listProductsPending = new ArrayList<>();

    static int id;
    static String name, note_info;
    static boolean state_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pending_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar botones y spinner
        btnSave = findViewById(R.id.btnSavePending);
        btnCancel = findViewById(R.id.btnCancelPending);
        spinnerProductsPending = findViewById(R.id.spinnerProductPending);


        //Nos traemos los datos del activity anterior
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        note_info = intent.getStringExtra("note_info");
        state_buy = intent.getBooleanExtra("state_buy", false);

        fillSpiner();
    }

    private void fillSpiner() {
        // Limpiamos la lista pendiente antes de llenarla
        listProductsPending.clear();

        // Recorremos la lista de productos de MainActivity
        for (Producto producto : MainActivity.listProducts) {
            // Solo añadimos los que tienen state_buy = false a la lista pendiente
            if (!producto.isState_buy()) {
                listProductsPending.add(producto);
            }
        }

        // Creamos el adaptador con la lista filtrada
        ArrayAdapter<Producto> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                listProductsPending
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductsPending.setAdapter(adapter);
    }

    public void savePending(View vista) {
        // Obtener el producto seleccionado del Spinner
        Producto productoSeleccionado = (Producto) spinnerProductsPending.getSelectedItem();

        if (productoSeleccionado != null) {
            // Buscar el producto en la lista principal y actualizar su estado
            for (Producto producto : MainActivity.listProducts) {
                // Si encontramos el producto, actualizamos su estado
                if (producto.getId() == productoSeleccionado.getId()) {
                    producto.setState_buy(true);
                    break;
                }
            }

            // Volver al MainActivity
            Intent intent = new Intent(PendingProduct.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra esta Activity
        }
    }


    public void cancelPending(View vista) {
        finish();
    }



}
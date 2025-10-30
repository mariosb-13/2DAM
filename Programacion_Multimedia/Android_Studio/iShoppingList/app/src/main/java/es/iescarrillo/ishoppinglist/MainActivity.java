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

public class MainActivity extends AppCompatActivity {

    Button btnAddProduct,btnAddPending,btnDetails;
    Spinner spinnerProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajuste de Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar botones y spinner
        btnAddProduct = findViewById(R.id.button_addProduct);
        btnAddPending = findViewById(R.id.button_addPending);
        btnDetails = findViewById(R.id.buttonDetails);
        spinnerProducts = findViewById(R.id.spinner);

        // Llenar spinner al iniciar
        llenarSpinner();
    }

    private void llenarSpinner() {
        ArrayList<Producto> listProducts = new ArrayList<>();
        listProducts.add(new Producto(1, "Patata", "Campo", true));
        listProducts.add(new Producto(2, "Tomate", "Invernadero", true));
        listProducts.add(new Producto(3, "Lechuga", "Hidroponia", false));

        ArrayAdapter<Producto> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listProducts);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProducts.setAdapter(adapter);
    }

    public View.OnClickListener showDetails(View vista){
        Producto selectedProduct = (Producto) spinnerProducts.getSelectedItem();
        Intent intent = new Intent(MainActivity.this, InfoProduct.class);
        intent.putExtra("producto",selectedProduct);
        startActivity(intent);
        return null;
    }

}

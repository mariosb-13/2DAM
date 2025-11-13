package es.iescarrillo.ishoppinglist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button btnAddProduct, btnAddPending, btnDetails;
    ListView lvProducts;

    ProductAdapter adapter;
    static ArrayList<Producto> listProducts = new ArrayList<>();
    static int id;
    static String name, note_info;
    static boolean state_buy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnAddProduct = findViewById(R.id.button_addProduct);
        btnAddPending = findViewById(R.id.button_addPending);
        btnDetails = findViewById(R.id.buttonDetails);
        lvProducts = findViewById(R.id.listView);

        lvProducts.setOnItemClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        note_info = intent.getStringExtra("note_info");
        state_buy = intent.getBooleanExtra("state_buy", false);

        fillListView();
        adapter = new ProductAdapter(this, R.layout.item_product, listProducts);
        lvProducts.setAdapter(adapter);

    }

    private void fillListView() {
        if (listProducts.isEmpty()) {
            listProducts.add(new Producto(1, "Patata", "Campo", true,R.drawable.img_product));
            listProducts.add(new Producto(2, "Tomate", "Invernadero", true,R.drawable.img_product));
            listProducts.add(new Producto(3, "Lechuga", "Hidroponia", false,R.drawable.img_product));
        }

        lvProducts.setAdapter(adapter);
    }

    public static void updateSpinner(Context context, int id, String name, String note_info, boolean state_buy) {
        for (Producto producto : listProducts) {
            if (producto.getId() == id) {
                Toast.makeText(context, "El producto con id: " + id + " está repetido", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Producto productoNuevo = new Producto(id, name, note_info, state_buy,R.drawable.img_product);
        listProducts.add(productoNuevo);
    }

    public void showDetails(View vista) {
        Producto selectedProduct = (Producto) lvProducts.getSelectedItem();
        Intent intent = new Intent(MainActivity.this, InfoProduct.class);
        intent.putExtra("id", selectedProduct.getId());
        intent.putExtra("name", selectedProduct.getName());
        intent.putExtra("note_info", selectedProduct.getNote_info());
        intent.putExtra("state_buy", selectedProduct.isState_buy());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillListView();
    }

    public void addProduct(View vista) {
        Intent intent = new Intent(MainActivity.this, AddProduct.class);
        startActivity(intent);
    }

    public void addPending(View vista) {
        Intent intent = new Intent(MainActivity.this, PendingProduct.class);
        startActivity(intent);
    }

    public void cancel(View vista) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

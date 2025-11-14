package es.iescarrillo.recyclerviewconanimacion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Producto> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerProducts);

        productList = new ArrayList<>();
        productList.add(new Producto("Pan artesanal", 2.99, R.drawable.pan, false));
        productList.add(new Producto("Galletas de avena", 3.49, R.drawable.pan, true));
        productList.add(new Producto("Pizza sin gluten", 8.99, R.drawable.pan, true));
        productList.add(new Producto("Cereal integral", 4.50, R.drawable.pan, false));
        productList.add(new Producto("Pan artesanal", 2.99, R.drawable.pan, false));
        productList.add(new Producto("Galletas de avena", 3.49, R.drawable.pan, true));
        productList.add(new Producto("Pizza sin gluten", 8.99, R.drawable.pan, true));
        productList.add(new Producto("Cereal integral", 4.50, R.drawable.pan, false));

        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
        recyclerView.scheduleLayoutAnimation();

    }
}

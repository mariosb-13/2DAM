package es.iescarrillo.recyclerviewsinanimacion;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductoAdapter adapter;
    ArrayList<Producto> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewProducts);

        productList = new ArrayList<>();
        productList.add(new Producto("Pan integral", 2.5, false, R.drawable.pan));
        productList.add(new Producto("Galletas sin gluten", 3.0, true, R.drawable.galletas));
        productList.add(new Producto("Pasta tradicional", 1.8, false, R.drawable.pan));
        productList.add(new Producto("Cereal de maíz", 2.2, true, R.drawable.galletas));

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //Muestra los elementos verticalmente
        adapter = new ProductoAdapter(this,productList);
        recyclerView.setAdapter(adapter);
    }
}

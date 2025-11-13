package es.iescarrillo.listviewpersonalizadoconimagen;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayList<Persona> listaPersonas;
    PersonaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewPersonas);
        listView.setOnItemClickListener(this);

        listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("Juan Pérez", 25, R.drawable.ic_persona));
        listaPersonas.add(new Persona("María López", 30, R.drawable.ic_persona));
        listaPersonas.add(new Persona("Pedro García", 22, R.drawable.ic_persona));
        listaPersonas.add(new Persona("Lucía Fernández", 28, R.drawable.ic_persona));

        adapter = new PersonaAdapter(this, R.layout.item_persona, listaPersonas);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Elemento seleccionado: "+position,Toast.LENGTH_SHORT).show();
    }
}

package es.iescarrillo.elementospinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button boton;

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

        spinner = findViewById(R.id.spinner);
        boton = findViewById(R.id.buttonRellenar);

        //Acción al botón
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {llenarSpinner();}
        });

        //Accion al spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Obtener el elemento seleccionado del spinner
                String seleccion = parent.getItemAtPosition(position).toString();

                //Mostrar mensaje
                Toast.makeText(MainActivity.this, "Seleccionaste:" + seleccion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Mostrar mensaje
                Toast.makeText(MainActivity.this, "No seleccionaste nada:" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void llenarSpinner(){
        Toast.makeText(MainActivity.this, "Estoy en el método llenar Spinner:", Toast.LENGTH_SHORT).show();

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario(1,"Mario","Sánchez","Barroso"));
        usuarios.add(new Usuario(2,"Juan","López","Benitez"));
        usuarios.add(new Usuario(3,"Elena","Rodriguez","Sánchez"));

        //Muestro los datos del usuario a nuestro spinner a través de un adapter
        ArrayAdapter<Usuario>adapter= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, usuarios);
        spinner.setAdapter(adapter);
    }



}
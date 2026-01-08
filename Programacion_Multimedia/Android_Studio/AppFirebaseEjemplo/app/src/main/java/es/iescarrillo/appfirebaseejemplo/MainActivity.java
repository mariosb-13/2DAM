package es.iescarrillo.appfirebaseejemplo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private EditText txtid, txtnom;
    private Button btnbus, btnmod, btnreg, btneli;
    private ListView lvDatos;

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

        txtid = findViewById(R.id.txtid);
        txtnom = findViewById(R.id.txtnom);
        btnbus = findViewById(R.id.btnbus);
        btnmod = findViewById(R.id.btnmod);
        btnreg = findViewById(R.id.btnreg);
        btneli = findViewById(R.id.btneli);
        lvDatos = findViewById(R.id.lvDatos);

        botonRegistrar();
        listarLuchadores();
        botonBuscar();
        botonEliminar();
        botonModificar();
    }

    private void botonModificar() {}

    private void botonEliminar() {}

    private void botonBuscar() {}

    private void listarLuchadores() {}

    private void botonRegistrar() {
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtid.getText().toString().isEmpty() || txtnom.getText().toString().isEmpty()) {
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();

                    //Conexion a la base de datos
                    FirebaseDatabase db = FirebaseDatabase.getInstance();

                    //Referencia al nodo de la base de datos
                    DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Luchador lu = new Luchador();
                            lu.setId(id);
                            lu.setNombre(nom);
                            //Tambien se puede hacer asi, mediante el constructor
                            //Luchador lu = new Luchador(id,nom);

                            // Se lanzan los datos a la base de datos
                            dbref.push().setValue(lu);
                            ocultarTeclado();
                            Toast.makeText(MainActivity.this, "Registro de luchador exitoso", Toast.LENGTH_SHORT).show();

                            //Limpiamos los campos
                            txtid.setText("");
                            txtnom.setText("");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
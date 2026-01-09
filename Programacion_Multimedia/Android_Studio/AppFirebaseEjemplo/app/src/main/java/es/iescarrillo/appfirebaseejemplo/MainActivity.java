package es.iescarrillo.appfirebaseejemplo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

        txtid   = findViewById(R.id.txtid);
        txtnom  = findViewById(R.id.txtnom);
        btnbus  = findViewById(R.id.btnbus);
        btnmod  = findViewById(R.id.btnmod);
        btnreg  = findViewById(R.id.btnreg);
        btneli  = findViewById(R.id.btneli);
        lvDatos = findViewById(R.id.lvDatos);

        botonRegistrar();
        listarLuchadores();
        botonBuscar();
        botonModificar();
        botonEliminar();

    } // Cierra el "onCreate".



    private void botonRegistrar(){
        btnreg.setOnClickListener(view -> {

            if(txtid.getText().toString().trim().isEmpty()
                    || txtnom.getText().toString().trim().isEmpty()){

                ocultarTeclado();
                Toast.makeText(MainActivity.this, "Complete Los Campos Faltantes!!", Toast.LENGTH_SHORT).show();

            }else{

                int id = Integer.parseInt(txtid.getText().toString());
                String nom = txtnom.getText().toString().trim();

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

                //También se puede hacer referencia al nodo hijo Luchador
                //// DatabaseReference dbref = db.getReference().child("Luchador");

                //Elemento de acción de Firebase - Se ejecuta una sola vez
                // Se puede utilizar el evento addValueEventListener de igual forma. Este se queda activo siempre. Se ejecuta una vez y queda escuchando cualquier cambio
                //Si lo utilizamos debemos eliminar la escucha manualmente, utilizando el siguiente código
                /*protected void onStop() {
                    super.onStop();
                    ref.removeEventListener(listener);
                }*/
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        boolean res = false;
                        boolean res2 = false;
                        String aux = Integer.toString(id);
                        for(DataSnapshot x:snapshot.getChildren()){

                            if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                res = true;
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Error. El id "+ aux+" ya existe", Toast.LENGTH_SHORT).show();

                            }
                            if(x.child("nombre").getValue().toString().equalsIgnoreCase(nom)){
                                res2 = true;
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Error. El nombre "+ nom+" ya existe", Toast.LENGTH_SHORT).show();

                            }
                        }
                        if(res == false && res2 == false){
                            Luchador luc = new Luchador(id, nom);
                            dbref.push().setValue(luc);
                            ocultarTeclado();
                            Toast.makeText(MainActivity.this, "Luchador Agregado Correctamente!!", Toast.LENGTH_SHORT).show();
                            txtid.setText("");
                            txtnom.setText("");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Error!!", Toast.LENGTH_SHORT).show();

                    }
                });

            } // Cierra el if/else inicial.

        });
    } // Cierra la función "botonRegistrar".





    private Task<Void> agregarLuchador(Luchador luc){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());
        return dbref.push().setValue(luc);
    } // Cierra la función "agregarLuchador".





    private void listarLuchadores(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

        ArrayList <Luchador> lisluc = new ArrayList<>();
        ArrayAdapter <Luchador> ada = new ArrayAdapter <Luchador> (MainActivity.this, android.R.layout.simple_list_item_1, lisluc);
        lvDatos.setAdapter(ada);

        //Hasta aquí no tengo ningún dato
        //Necesito añadir los registros hijos de la BBDD
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            //Al agregar registros nuevos en la BBDD, se recarga la listView
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Luchador luc = snapshot.getValue(Luchador.class);
                lisluc.add(luc);
                ada.notifyDataSetChanged();//Refrescamos el listView

            }

            @Override
            //Cuando se produzcan cambios
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ada.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Evento de pulsación al item del listView
        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ocultarTeclado();
                Luchador luc = lisluc.get(position);
                AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                a.setCancelable(true);
                a.setTitle("Luchados Seleccionado");

                String msg = "ID" + luc.getId()+"\n\n";
                msg += "nombre: " + luc.getNombre();

                a.setMessage(msg);
                a.show();

            }
        });


        //Selección prolongada del item del listView para borrarlo
        lvDatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                Luchador luc = lisluc.get(position);

                String aux = Integer.toString(luc.getId());

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        boolean res = false;
                        for(DataSnapshot x : snapshot.getChildren()){
                            if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                res = true;

                                AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                                a.setTitle("Pregunta");
                                a.setMessage("¿Está Seguro(a) De Querer Eliminar El Registro ("+aux+")?");
                                a.setCancelable(false);

                                a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                                a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        x.getRef().removeValue();
                                        listarLuchadores();
                                        ocultarTeclado();
                                        Toast.makeText(MainActivity.this, "Registro ("+aux+") Eliminado Correctamente!!", Toast.LENGTH_SHORT).show();
                                        txtid.setText("");
                                        txtnom.setText("");
                                        txtid.requestFocus();

                                    }
                                });

                                a.show();
                                break;


                            }
                        }

                        if(res == false){
                            ocultarTeclado();
                            Toast.makeText(MainActivity.this, "Error. Id ("+aux+") No Encontrado. Imposible Eliminar!!", Toast.LENGTH_SHORT).show();
                            txtid.setText("");
                            txtnom.setText("");
                            txtid.requestFocus();
                        }

                    } // Cierra el "onDataChange".

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return true;
            }
        });


    } // Cierra la función "listarLuchadores".





    private void botonBuscar(){
        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Indique El Id Para Buscar!!", Toast.LENGTH_SHORT).show();
                    txtid.setText("");
                    txtid.requestFocus();
                }else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    String aux = Integer.toString(id);

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

                    //Evento que accede al contenedor
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        //Es como una consulta parametrizada en la BBDD
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;
                                    txtnom.setText(x.child("nombre").getValue().toString());
                                    break;
                                }
                            }

                            if(!res){
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Error. Id ("+aux+") No Encontrado!!", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                                txtid.requestFocus();
                            }

                        } // Cierra el "onDataChange".

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } // Cierra el if/else inicial.

            }
        });
    } // Cierra la función "botonBuscar".





    private void botonModificar(){
        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()
                        || txtnom.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Complete Los Campos Para Continuar!!!!", Toast.LENGTH_SHORT).show();
                    txtid.setText("");
                    txtnom.setText("");
                    txtid.requestFocus();
                }else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    String aux = Integer.toString(id);

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res = false;
                            boolean res2 = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;
                                }

                                if(x.child("nombre").getValue().toString().equalsIgnoreCase(nom)){
                                    res2 = true;
                                }

                                // Si el ID existe y si el nombre es nuevo (NO existe), se procede a modificar.
                                if(res == true && res2 == false){
                                    AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                                    a.setTitle("Pregunta");
                                    a.setMessage("¿Está Seguro(a) De Querer Modificar El Nombre Del Registro ("+aux+")?");
                                    a.setCancelable(false);

                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });

                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            x.getRef().child("nombre").setValue(nom);
                                            listarLuchadores();
                                            ocultarTeclado();
                                            Toast.makeText(MainActivity.this, "Dato Modificado Correctamente!!", Toast.LENGTH_SHORT).show();
                                            txtid.setText("");
                                            txtnom.setText("");
                                            txtid.requestFocus();

                                        }
                                    });

                                    a.show();
                                    break;
                                } // Cierra el if res y res2.

                            } // Cierra el ciclo "for".

                            if(res == false){
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Error. Id ("+aux+") No Encontrado. Imposible Modificar Nombre!!", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                                txtid.requestFocus();
                            }else if(res2 == true){
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Error. El Nombre ("+nom+") Ya Existe. Imposible Modificar Nombre En Uso!!", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                                txtid.requestFocus();
                            }

                        } // Cierra el "onDataChange".

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } // Cierra el if/else inicial.

            }
        });
    } // Cierra la función "botonModificar".





    private void botonEliminar(){
        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Indique El Id Para Eliminar!!", Toast.LENGTH_SHORT).show();
                    txtid.setText("");
                    txtid.requestFocus();
                }else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    String aux = Integer.toString(id);

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Luchador.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    res = true;

                                    AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                                    a.setTitle("Pregunta");
                                    a.setMessage("¿Está Seguro(a) De Querer Eliminar El Registro ("+aux+")?");
                                    a.setCancelable(false);

                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });

                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            x.getRef().removeValue();
                                            listarLuchadores();
                                            ocultarTeclado();
                                            Toast.makeText(MainActivity.this, "Registro ("+aux+") Eliminado Correctamente!!", Toast.LENGTH_SHORT).show();
                                            txtid.setText("");
                                            txtnom.setText("");
                                            txtid.requestFocus();

                                        }
                                    });

                                    a.show();
                                    break;


                                }
                            }

                            if(res == false){
                                ocultarTeclado();
                                Toast.makeText(MainActivity.this, "Error. Id ("+aux+") No Encontrado. Imposible Eliminar!!", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                                txtid.requestFocus();
                            }

                        } // Cierra el "onDataChange".

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } // Cierra el if/else inicial.

            }
        });
    } // Cierra la función "botonEliminar".





    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.



} // Cierra la clase "MainActivity".
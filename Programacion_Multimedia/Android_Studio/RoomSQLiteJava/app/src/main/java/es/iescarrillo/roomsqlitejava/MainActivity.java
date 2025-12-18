package es.iescarrillo.roomsqlitejava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvUsuarios;

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

        setContentView(R.layout.activity_main);

        tvUsuarios = findViewById(R.id.tvUsuarios);

        List<Usuario> listaUsuario;

        //CONSTRUCCIÓN DE LA BBDD
        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().build(); //Permitir consultas en nuestro hilo principal

        // INSERTAR USUARIOS EN LA BBDD
      /*  appDatabase.daoUSuario().insertarUsuario(new Usuario("1", "Ang", "correoAng@iescarrillo.es"));
        appDatabase.daoUSuario().insertarUsuario(new Usuario("2", "Pacp", "correoPacp@iescarrillo.es"));
        appDatabase.daoUSuario().insertarUsuario(new Usuario("3", "Pepe", "correoPepe@iescarrillo.es"));*/

        //MOSTRAR USUARIOS DE LA BBDD
     /*   listaUsuario = appDatabase.daoUSuario().obtenerUsuarios();
        String texto = "";
        for(int i=0;i<listaUsuario.size();i++){
            texto=texto+"Usuario"+i+"="+listaUsuario.get(i).getUsuario()+listaUsuario.get(i).getNombre()+listaUsuario.get(i).getCorreo()+"\n";
        }
        tvUsuarios.setText(texto);*/


        //MOSTRAR UN USUARIO DE LA BBDD
       // Usuario user = appDatabase.daoUSuario().obtenerUsuario("1");
       // tvUsuarios.setText(user.getUsuario()+" "+user.getCorreo()+" "+user.getNombre() );

        //ACTUALIZAR UN USUARIO DE LA BBDD
        //appDatabase.daoUSuario().actualizarUsuario("1","Angélica Prados", "nuevocorreo.es");

        //ELIMINAMOS DE LA BBDD
        appDatabase.daoUSuario().borrarUsuario("1");


        //VISUALIZAMOS
        listaUsuario = appDatabase.daoUSuario().obtenerUsuarios();
        String texto = "";
        for(int i=0;i<listaUsuario.size();i++){
            texto=texto+"Usuario"+i+"="+listaUsuario.get(i).getUsuario()+listaUsuario.get(i).getNombre()+listaUsuario.get(i).getCorreo()+"\n";
        }
        tvUsuarios.setText(texto);


    }





}
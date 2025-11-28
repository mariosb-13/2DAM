package es.iescarrillo.sqlitevariastablasmaselementos;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    public void onClick(View view) {
        Intent miIntent=null;
        if(view.getId()==R.id.btnOpcionRegistro){
            miIntent=new Intent(MainActivity.this,RegistroUsuariosActivity.class);

        }else if(view.getId()==R.id.btnRegistroMascota){
            miIntent=new Intent(MainActivity.this,RegistroMascotaActivity.class);

        }else if(view.getId()==R.id.btnConsultaIndividual){
            miIntent=new Intent(MainActivity.this,ConsultarUsuariosActivity.class);

        }else if(view.getId()==R.id.btnConsultaSpinner){
            miIntent=new Intent(MainActivity.this,ConsultaComboActivity.class);

        }else if(view.getId()==R.id.btnConsultaLista){
            miIntent=new Intent(MainActivity.this,ConsultarListaListViewActivity.class);

        }else if(view.getId()==R.id.btnConsultaListaMascota){
            miIntent=new Intent(MainActivity.this,ListaMascotasActivity.class);

        }else if(view.getId()==R.id.btnConsultaListaPersonasRecycler){
            miIntent=new Intent(MainActivity.this,ListaPersonasRecycler.class);
        }

        if (miIntent!=null){
            startActivity(miIntent);
        }

    }
}
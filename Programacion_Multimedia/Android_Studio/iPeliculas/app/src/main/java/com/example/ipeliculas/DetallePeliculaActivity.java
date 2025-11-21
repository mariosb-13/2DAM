package com.example.ipeliculas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetallePeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        // Configurar la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Localizar los elementos de la vista
        TextView tvTitulo = findViewById(R.id.tvTituloDetalle);
        TextView tvGenero = findViewById(R.id.tvGeneroDetalle);
        TextView tvSinopsis = findViewById(R.id.tvSinopsisDetalle);
        TextView tvDirector = findViewById(R.id.tvDirectorDetalle);
        TextView tvFechaEstreno = findViewById(R.id.tvFechaEstrenoDetalle);
        ImageView imgDetalle = findViewById(R.id.imgDetalle);
        RatingBar ratingBar = findViewById(R.id.ratingDetalle);

       // Usamos las mismas CLAVES ("TITULO", etc.) que usamos en el Adapter
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String titulo = extras.getString("TITULO");
            String genero = extras.getString("GENERO");
            String sinopsis = extras.getString("SINOPSIS");
            String director = extras.getString("DIRECTOR");
            String fechaEstreno = extras.getString("FECHA_ESTRENO");
            int imagenResId = extras.getInt("IMAGEN");
            float valoracion = extras.getFloat("VALORACION");

            //Poner los datos en la pantalla
            tvTitulo.setText(titulo);
            tvGenero.setText(genero);
            tvSinopsis.setText(sinopsis);
            tvDirector.setText("Director: "+director);
            tvFechaEstreno.setText("Fecha de estreno: "+fechaEstreno);
            imgDetalle.setImageResource(imagenResId);
            ratingBar.setRating(valoracion);

            // Opcional: Cambiar el título de la barra superior
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Detalle de la película");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    /**
     * 1. INFLAR EL MENÚ
     * Este método se llama una vez para crear el menú en la barra de acción.
     * Carga el XML del menú (res/menu/main_menu.xml) en la interfaz.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Carga el archivo main_menu.xml en el objeto Menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * 2. MANEJAR CLICS
     * Este método se llama cada vez que el usuario selecciona un ítem del menú.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Obtenemos el ID del ítem que ha sido pulsado
        int id = item.getItemId();

        if (id == R.id.acercaDe) {
            Intent intent = new Intent(this, AboutActivity.class);

            startActivity(intent);
            return true;
        }else if(id == R.id.mapsCine){
            Intent intent = new Intent(this, ActivityGoogleMaps.class);
            startActivity(intent);
            return true;
        }

        // Si el ítem no fue manejado por nuestro código, dejamos que el sistema lo maneje.
        return super.onOptionsItemSelected(item);
    }

    // Para que funcione la flecha de atrás de la barra
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
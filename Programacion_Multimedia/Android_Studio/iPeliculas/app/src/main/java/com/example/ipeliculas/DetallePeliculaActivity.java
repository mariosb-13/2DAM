package com.example.ipeliculas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetallePeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        // Configurar la Toolbar
        // Importa: androidx.appcompat.widget.Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 1. Localizar los elementos de la vista
        TextView tvTitulo = findViewById(R.id.tvTituloDetalle);
        TextView tvGenero = findViewById(R.id.tvGeneroDetalle);
        TextView tvSinopsis = findViewById(R.id.tvSinopsisDetalle);
        ImageView imgDetalle = findViewById(R.id.imgDetalle);
        RatingBar ratingBar = findViewById(R.id.ratingDetalle);

        // 2. Recuperar los datos del Intent
        // Usamos las mismas CLAVES ("TITULO", etc.) que usamos en el Adapter
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String titulo = extras.getString("TITULO");
            String genero = extras.getString("GENERO");
            String sinopsis = extras.getString("SINOPSIS");
            int imagenResId = extras.getInt("IMAGEN");
            float valoracion = extras.getFloat("VALORACION");

            // 3. Poner los datos en la pantalla
            tvTitulo.setText(titulo);
            tvGenero.setText(genero);
            tvSinopsis.setText(sinopsis);
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

        //Establecemos el titulo del action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cartelera de Cine");
        }

        return true;

    }

    // Para que funcione la flecha de atrás de la barra
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
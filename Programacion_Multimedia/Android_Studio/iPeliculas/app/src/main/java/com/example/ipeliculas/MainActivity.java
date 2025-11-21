package com.example.ipeliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeliculaAdapter adaptadorPeli;
    private List<Pelicula> listaPeliculas;

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

        // Configurar la Toolbar
        // Importa: androidx.appcompat.widget.Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyclerView);
        listaPeliculas= new ArrayList<>();

        listaPeliculas.add(new Pelicula("Titanic", "Drama", 4.5f,
                "Un joven artista y una aristócrata se enamoran a bordo del insumergible RMS Titanic, desafiando las normas sociales mientras el barco se dirige hacia un trágico destino en las heladas aguas del Atlántico Norte.",
                R.drawable.titanic_portada,
                "James Cameron", "1997"));

        listaPeliculas.add(new Pelicula("Avatar", "Ciencia Ficción", 4.8f,
                "En el exuberante mundo alienígena de Pandora, un exmarine paralítico ocupa su lugar en un programa que le permite controlar un cuerpo biológico híbrido, pero pronto se encuentra dividido entre seguir órdenes y proteger a la civilización nativa.",
                R.drawable.avatar_portada,
                "James Cameron", "2009"));

        listaPeliculas.add(new Pelicula("El Padrino", "Crimen", 5.0f,
                "El envejecido patriarca de una dinastía del crimen organizado en la posguerra de Nueva York transfiere el control de su imperio clandestino a su hijo reacio, desatando una serie de traiciones y guerras entre familias mafiosas.",
                R.drawable.padrino_portada,
                "Francis Ford Coppola", "1972"));

        listaPeliculas.add(new Pelicula("Matrix", "Ciencia Ficción", 4.7f,
                "Un hacker informático aprende de misteriosos rebeldes la verdadera naturaleza de su realidad y su papel en la guerra contra sus controladores: una inteligencia artificial poderosa que mantiene a la humanidad dormida en una simulación.",
                R.drawable.matrix_portada,
                "Lana y Lilly Wachowski", "1999"));

        listaPeliculas.add(new Pelicula("Inception", "Thriller", 4.6f,
                "Un ladrón experto en el peligroso arte de la extracción, robar secretos del subconsciente durante el estado de sueño, recibe una última oportunidad de redención si logra lo imposible: implantar una idea en la mente de un heredero corporativo.",
                R.drawable.inception_portada,
                "Christopher Nolan", "2010"));

        listaPeliculas.add(new Pelicula("Pulp Fiction", "Crimen", 4.9f,
                "Las vidas de dos asesinos a sueldo de la mafia, un boxeador, la esposa de un gánster y un par de bandidos de cafetería se entrelazan en cuatro historias de violencia y redención en Los Ángeles.",
                R.drawable.pulp_portada,
                "Quentin Tarantino", "1994"));

        listaPeliculas.add(new Pelicula("Jurassic Park", "Ciencia Ficción", 4.4f,
                "Un multimillonario invita a un grupo de científicos a su parque temático en una isla remota, donde ha logrado clonar dinosaurios reales. Sin embargo, una falla de seguridad convierte la visita en una pesadilla de supervivencia prehistórica.",
                R.drawable.jurassic_portada,
                "Steven Spielberg", "1993"));

        listaPeliculas.add(new Pelicula("Forrest Gump", "Drama", 4.8f,
                "Las presidencias de Kennedy y Johnson, la guerra de Vietnam y otros eventos históricos se desarrollan a través de la perspectiva de un hombre de Alabama con un coeficiente intelectual bajo, cuya inocencia altera la historia.",
                R.drawable.forrest_portada,
                "Robert Zemeckis", "1994"));

        adaptadorPeli=new PeliculaAdapter(listaPeliculas,this);
        recyclerView.setAdapter(adaptadorPeli);

        recyclerView = findViewById(R.id.recyclerView);
        adaptadorPeli = new PeliculaAdapter(listaPeliculas, this);
        recyclerView.setAdapter(adaptadorPeli);

        // Le dice al Recycler que se comporte como una lista vertical estándar
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.scheduleLayoutAnimation();
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
}

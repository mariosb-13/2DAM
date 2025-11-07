package es.iescarrillo.listviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.AbsListView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        AdapterView.OnItemSelectedListener,
        AbsListView.OnScrollListener {

    ListView listView;
    ArrayList<String> listaNombres;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1️⃣ Referencia al ListView
        listView = findViewById(R.id.listView);

        // 2️⃣ Crear el ArrayList con datos
        listaNombres = new ArrayList<>();
        listaNombres.add("Juan");
        listaNombres.add("María");
        listaNombres.add("Pedro");
        listaNombres.add("Lucía");

        // 3️⃣ Crear el ArrayAdapter (contexto, layout de cada ítem, lista de datos)
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNombres);

        // 4️⃣ Asignar el adapter al ListView
        listView.setAdapter(adapter);

        // 5️⃣ Asignar los listeners (eventos)
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemSelectedListener(this);
        listView.setOnScrollListener(this);
    }

    /**
     * Evento: clic corto en un ítem
     * @param parent The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked (this
     *            will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Click en: " + nombre, Toast.LENGTH_SHORT).show();
    }

    /**
     * Evento: clic largo en un ítem
     * @param parent The AbsListView where the click happened
     * @param view The view within the AbsListView that was clicked
     * @param position The position of the view in the list
     * @param id The row id of the item that was clicked
     *
     * @return
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Click largo en: " + nombre, Toast.LENGTH_SHORT).show();
        return true; // true = evento consumido
    }

    /**
     * Evento: ítem seleccionado
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nombre = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Seleccionado: " + nombre, Toast.LENGTH_SHORT).show();
    }

    /**
     * Evento: nada seleccionado
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nada seleccionado", Toast.LENGTH_SHORT).show();
    }

    /**
     * Evento: cambio en el estado del scroll
     * @param view The view whose scroll state is being reported
     *
     * @param scrollState The current scroll state. One of
     * {@link #SCROLL_STATE_TOUCH_SCROLL} or {@link #SCROLL_STATE_IDLE}.
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                Toast.makeText(this, "Scroll detenido", Toast.LENGTH_SHORT).show();
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Toast.makeText(this, "Desplazando con el dedo", Toast.LENGTH_SHORT).show();
                break;
            case SCROLL_STATE_FLING:
                Toast.makeText(this, "Desplazamiento rápido (fling)", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Evento: desplazamiento en curso
     * @param view The view whose scroll state is being reported
     * @param firstVisibleItem the index of the first visible cell (ignore if
     *        visibleItemCount == 0)
     * @param visibleItemCount the number of visible cells
     * @param totalItemCount the number of items in the list adapter
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // Este evento se lanza continuamente mientras haces scroll.
    }
}

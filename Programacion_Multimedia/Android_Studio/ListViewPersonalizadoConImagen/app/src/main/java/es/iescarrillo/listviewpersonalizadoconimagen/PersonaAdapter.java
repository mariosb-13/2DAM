package es.iescarrillo.listviewpersonalizadoconimagen;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

//Necesito pasar de lo que se va a nutrir el Adaptador, en este caso Persona
public class PersonaAdapter extends ArrayAdapter<Persona> {

    private List<Persona> mlist;
    private Context mcontext;
    private int resourceLayout;
//Contexto -- de donde traerá los datos
    //datos
    public PersonaAdapter(Context context,int resource, List<Persona> objects) {
        super(context, resource, objects);
        this.mlist=objects;
        this.resourceLayout=resource;
        this.mcontext= context;
    }

    //Buscamos las vistas que aparecerán en cada fila del listView personalizado
   // Este método se ejecuta una vez por cada elemento visible en la lista, y su trabajo es crear o reutilizar la vista que representa cada ítem.
   // @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Persona persona = getItem(position);
        //Es una vista que el sistema puede reciclar (reutilizar) para no crear una nueva cada vez.
       // Si es null, significa que no hay ninguna vista disponible para reutilizar → entonces hay que crear una nueva.
       // Si no es null, significa que puedes reutilizarla, simplemente actualizando su contenido (texto, imagen, etc.).

        if (convertView == null) {
           // Toma el layout XML del ítem y lo convierte en una vista lista para usar.
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_persona, null);
        }

        ImageView imgPersona = convertView.findViewById(R.id.imgPersona);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtEdad = convertView.findViewById(R.id.txtEdad);

        imgPersona.setImageResource(persona.getImagenResId());
        txtNombre.setText(persona.getNombre());
        txtEdad.setText("Edad: " + persona.getEdad() + " años");

        return convertView; //devuelve la lista con los elementos en cada posición
    }
}

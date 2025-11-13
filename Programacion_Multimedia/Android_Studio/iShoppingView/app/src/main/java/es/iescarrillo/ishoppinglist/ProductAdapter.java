package es.iescarrillo.ishoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Producto> {
    private List<Producto> mlist;
    private Context mcontext;
    private int resourceLayout;

    //Contexto -- de donde traerá los datos
    //datos
    public ProductAdapter(Context context,int resource, List<Producto> objects) {
        super(context, resource, objects);
        this.mlist=objects;
        this.resourceLayout=resource;
        this.mcontext= context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Producto product = getItem(position);
        //Es una vista que el sistema puede reciclar (reutilizar) para no crear una nueva cada vez.
        // Si es null, significa que no hay ninguna vista disponible para reutilizar → entonces hay que crear una nueva.
        // Si no es null, significa que puedes reutilizarla, simplemente actualizando su contenido (texto, imagen, etc.).

        if (convertView == null) {
            // Toma el layout XML del ítem y lo convierte en una vista lista para usar.
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_product, null);
        }

        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView txtNombre = convertView.findViewById(R.id.tvNombre);
        TextView txtInfo = convertView.findViewById(R.id.tvInfo);

        imgProduct.setImageResource(product.getImage());
        txtNombre.setText(product.getName());
        txtInfo.setText(product.getNote_info());
        return convertView; //
    }
}

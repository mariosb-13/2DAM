package es.iescarrillo.recyclerviewsinanimacion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private ArrayList<Producto> productList;
    private int lastPosition = -1;

    //Constructor, lo que hacemos es inicializar el contexto y la lista de objetos
    public ProductoAdapter(Context c, ArrayList<Producto> productList) {
        this.productList = productList;
    }


    //Establecemos qué fichero xml tiene la interfaz gráfica de cada uno de los elementos de nuestro RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    //Para cada uno de los objetos establece sus datos, en nuestro caso nombre, precio, gluten e imagen.
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Producto product = productList.get(position);


        holder.textName.setText(product.getName());
        holder.textPrice.setText("€" + product.getPrice());
        holder.textGluten.setText(product.isGlutenFree() ? "Sin gluten" : "Con gluten");
        holder.imageProduct.setImageResource(product.getImageResId());

        // Animación simple al aparecer
        //Evita que los ítems ya mostrados vuelvan a animarse al hacer scroll hacia arriba o reciclar vistas.
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),
                    android.R.anim.slide_in_left);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    //El metodo getItemCount() le dice al RecyclerView cuántos elementos debe mostrar.
    @Override
    public int getItemCount() {
        return productList.size();
    }

    //Enlaza los elementos de la interfaz gráfica con las variables del adaptador
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView textName, textPrice, textGluten;

       //itemView representa la vista completa de un solo ítem (el layout XML inflado en onCreateViewHolder()).
        public ViewHolder(View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            textGluten = itemView.findViewById(R.id.textGluten);
        }
    }
}
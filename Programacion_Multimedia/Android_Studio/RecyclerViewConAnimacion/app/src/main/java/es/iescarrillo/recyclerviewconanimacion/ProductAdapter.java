package es.iescarrillo.recyclerviewconanimacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Producto> productList;
    private Context context;

    public ProductAdapter(Context context, List<Producto> productList) {
        this.context = context;
        this.productList = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice, tvGluten;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvGluten = itemView.findViewById(R.id.tvGluten);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Producto product = productList.get(position);

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("€" + product.getPrice());
        holder.tvGluten.setText(product.isGlutenFree() ? "Sin gluten" : "Contiene gluten");

        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.imgProduct);

        // 🔹 Ejecuta animación solo la primera vez que el ítem aparece
        if (holder.itemView.getAnimation() == null) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_fade_in);
            holder.itemView.startAnimation(animation);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

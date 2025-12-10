package es.iescarrillo.proyectosqlite.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.EditCocheActivity;
import es.iescarrillo.proyectosqlite.R;
import es.iescarrillo.proyectosqlite.dao.CocheDAO;
import es.iescarrillo.proyectosqlite.database.DatabaseHelper;
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.ViewHolder> {

    private ArrayList<Coche> lista;

    public CocheAdapter(ArrayList<Coche> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coche, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coche coche = lista.get(position);

        holder.txtMatricula.setText(coche.getMatricula());
        holder.txtModelo.setText(coche.getModelo());
        holder.txtPrecio.setText(coche.getPrecio_venta() + " €");

        holder.btnEditar.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, EditCocheActivity.class);
            intent.putExtra("coche_objeto", coche);
            context.startActivity(intent);
        });

        holder.btnEliminar.setOnClickListener(v -> {
            Context context = v.getContext();

            DatabaseHelper dbHelper = new DatabaseHelper(context);

            CocheDAO cocheDAO = new CocheDAO(dbHelper);

            cocheDAO.eliminarCoche(coche.getId_coche());

            updateList(cocheDAO.obtenerCoches());
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMatricula, txtModelo, txtPrecio;
        Button btnEditar, btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMatricula = itemView.findViewById(R.id.txtMatricula);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    public void updateList(ArrayList<Coche> nuevaLista) {
        lista = nuevaLista;
        notifyDataSetChanged();
    }
}
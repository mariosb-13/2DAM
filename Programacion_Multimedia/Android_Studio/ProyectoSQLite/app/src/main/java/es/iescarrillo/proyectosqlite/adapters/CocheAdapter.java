package es.iescarrillo.proyectosqlite.adapters;

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
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.ViewHolder> {

    private ArrayList<Coche> lista;
    // Ya no necesitamos las listas de spinners aquí

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coche coche = lista.get(position);

        // Textos
        holder.txtMatricula.setText(coche.getMatricula());
        holder.txtModelo.setText(coche.getModelo());
        holder.txtPrecio.setText(coche.getPrecio_venta() + " €");

        // Acción del Botón Editar
        holder.btnEditar.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, EditCocheActivity.class);

            intent.putExtra("coche_objeto", coche);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMatricula, txtModelo, txtPrecio;
        Button btnEditar; // Cambiamos Spinners por Botón

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMatricula = itemView.findViewById(R.id.txtMatricula);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }

    public void updateList(ArrayList<Coche> nuevaLista) {
        lista = nuevaLista;
        notifyDataSetChanged();
    }
}
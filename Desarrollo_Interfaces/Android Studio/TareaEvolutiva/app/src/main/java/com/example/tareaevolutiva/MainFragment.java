package com.example.tareaevolutiva;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.sidesheet.SideSheetDialog;

import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Constructor público requerido
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragment_main
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // REFERENCIAR EL BOTÓN DEL PERFIL
        ImageButton btnPerfil = view.findViewById(R.id.imgProfile);
        btnPerfil.setOnClickListener(v -> abrirMenuLateral());

        // Datos de las prendas
        List<Integer> camisetas = Arrays.asList(
                R.drawable.camiseta_travis,
                R.drawable.camiseta2,
                R.drawable.camiseta_off
        );

        List<Integer> pantalones = Arrays.asList(
                R.drawable.pantalones,
                R.drawable.nike_pantalones,
                R.drawable.pantalones_chandal
        );

        List<Integer> zapatillas = Arrays.asList(
                R.drawable.zapatillas_travis,
                R.drawable.akimbo,
                R.drawable.dunk
        );

        // Configurar RecyclerViews con su layout de ítem específico
        setupPrendaRecyclerView(view, R.id.recyclerCamisetas, camisetas, R.layout.item_camiseta);
        setupPrendaRecyclerView(view, R.id.recyclerPantalones, pantalones, R.layout.item_pantalon);
        setupPrendaRecyclerView(view, R.id.recyclerZapatillas, zapatillas, R.layout.item_zapatilla);
    }

    /**
     * Configura un RecyclerView para las prendas con Snap Scrolling.
     * @param fragmentView La vista raíz del fragment.
     * @param recyclerViewId El ID del RecyclerView.
     * @param dataSet La lista de IDs de drawable de las prendas.
     * @param layoutResId El ID del layout específico para el ítem.
     */
    private void setupPrendaRecyclerView(@NonNull View fragmentView, int recyclerViewId, List<Integer> dataSet, int layoutResId) {
        RecyclerView recyclerView = fragmentView.findViewById(recyclerViewId);
        Context context = requireContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Pasamos el ID del layout al adaptador
        PrendaAdapter adapter = new PrendaAdapter(dataSet, layoutResId);
        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * Método que abre el menu lateral (Side Sheet).
     */
    private void abrirMenuLateral() {
        SideSheetDialog sideSheet = new SideSheetDialog(requireContext());
        sideSheet.setContentView(R.layout.layout_side_sheet);

        Button btnLogout = sideSheet.findViewById(R.id.btnCerrarSesion);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                // Navegar a LoginActivity y finalizar la Activity host
                requireContext().startActivity(new Intent(requireContext(), LoginActivity.class));
                requireActivity().finish();
            });
        }
        sideSheet.show();
    }
}
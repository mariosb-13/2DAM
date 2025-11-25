package com.example.tareaevolutiva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.sidesheet.SideSheetDialog;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCIAR EL BOTÓN DEL PERFIL
        ImageButton btnPerfil = findViewById(R.id.imgProfile);

        // DARLE LA FUNCIONALIDAD
        btnPerfil.setOnClickListener(v -> abrirMenuLateral());

        //INICIALIZAR LAS LISTAS DE PRENDAS CON SNAP SCROLLING

        // Datos de las prendas (usando los drawables que pusiste en el XML original)
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

        // Configurar cada RecyclerView
        setupPrendaRecyclerView(R.id.recyclerCamisetas, camisetas);
        setupPrendaRecyclerView(R.id.recyclerPantalones, pantalones);
        setupPrendaRecyclerView(R.id.recyclerZapatillas, zapatillas);
    }

    /**
     * Configura un RecyclerView para las prendas con Snap Scrolling.
     * @param recyclerViewId El ID del RecyclerView.
     * @param dataSet La lista de IDs de drawable de las prendas.
     */
    private void setupPrendaRecyclerView(int recyclerViewId, List<Integer> dataSet) {
        RecyclerView recyclerView = findViewById(recyclerViewId);

        // Configurar el LayoutManager para desplazamiento HORIZONTAL
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Configurar el Adapter para mostrar las prendas
        PrendaAdapter adapter = new PrendaAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        // Esto hace que cada prenda se centre al finalizar el scroll.
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * Método que abre el menu lateral
     */
    private void abrirMenuLateral() {
        // Crea la instancia de sideSheet
        SideSheetDialog sideSheet = new SideSheetDialog(this);

        // Asignarle el diseño XML del menú
        sideSheet.setContentView(R.layout.layout_side_sheet);

        //Funcionalidad al boton de cerrar sesion
        Button btnLogout = sideSheet.findViewById(R.id.btnCerrarSesion);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        }
        // Mostrar el menú
        sideSheet.show();
    }
}
package com.example.fragmentsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
    onViewCreated() es el sitio correcto para manejar vistas.
    Se llama a newInstance() para crear fragmentos con argumentos.
    addToBackStack(null) permite volver atrás con el botón físico.
*/

public class ProductListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btnProduct1);
        Button btn2 = view.findViewById(R.id.btnProduct2);

        btn1.setOnClickListener(v -> openDetail("Producto 1"));
        btn2.setOnClickListener(v -> openDetail("Producto 2"));
    }

    private void openDetail(String productName) {

        ProductDetailFragment fragment = ProductDetailFragment.newInstance(productName);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}


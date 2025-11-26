package com.example.fragmentsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
    Se usa newInstance() para pasar datos de manera segura
    getArguments() recupera esos datos en el destino
    popBackStack() retorna al fragmento anterior
    Esto ya es navegación completa entre pantallas con datos
*/

public class ProductDetailFragment extends Fragment {

    private static final String ARG_PRODUCT = "product_name";

    public static ProductDetailFragment newInstance(String productName) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT, productName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtProduct = view.findViewById(R.id.txtProductName);
        Button btnBack = view.findViewById(R.id.btnBack);

        String productName = getArguments().getString(ARG_PRODUCT);
        txtProduct.setText("Has seleccionado: " + productName);

        btnBack.setOnClickListener(v ->
                getActivity().getSupportFragmentManager().popBackStack()
        );
    }
}

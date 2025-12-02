package es.iescarrillo.diseofigma;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VueloFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

    public VueloFragment() {
        // Constructor vacío requerido
    }

    // Método estático simple, sin parámetros innecesarios si no los usas
    public static VueloFragment newInstance() {
        return new VueloFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout
        View view = inflater.inflate(R.layout.fragment_vuelo, container, false);

        // 1. Inicializar el MapView
        mapView = view.findViewById(R.id.mapView);

        // 2. IMPORTANTE: Crear el mapa con el Bundle del ciclo de vida
        mapView.onCreate(savedInstanceState);

        // 3. Cargar el mapa asíncronamente
        mapView.getMapAsync(this);

        return view;
    }

    /**
     * Este método se ejecuta cuando el mapa está listo para usarse.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

        // 1. DESACTIVAR los controles nativos (los que salen por defecto)
        // Esto quita la brújula y los botones +/- originales para que no se dupliquen
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);

        // 2. CONFIGURAR TUS BOTONES NUEVOS
        // (Asegúrate de buscar las vistas con getView().findViewById si no usas ViewBinding)
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.btnZoomIn).setOnClickListener(v -> {
                // Animación de acercar
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            });

            view.findViewById(R.id.btnZoomOut).setOnClickListener(v -> {
                // Animación de alejar
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());
            });
        }

        // Resto de tu configuración (Marcadores, posición inicial, etc.)
        LatLng aeropuerto = new LatLng(40.472255, -3.560917);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aeropuerto, 12f));
    }

    // -----------------------------------------------------------
    // GESTIÓN OBLIGATORIA DEL CICLO DE VIDA DEL MAPVIEW
    // Si no pones esto, el mapa se verá blanco o crasheará.
    // -----------------------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        if (mapView != null) mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mapView != null) mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) mapView.onSaveInstanceState(outState);
    }
}
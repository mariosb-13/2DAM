package es.iescarrillo.diseofigma;

import android.graphics.Color;
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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

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

        // 1. DESACTIVAR los controles nativos
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);

        // 2. CONFIGURAR TUS BOTONES NUEVOS
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.btnZoomIn).setOnClickListener(v -> {
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            });

            view.findViewById(R.id.btnZoomOut).setOnClickListener(v -> {
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());
            });
        }


        LatLng puntoSalida = new LatLng(40.472255, -3.560917); // Aeropuerto Madrid
        LatLng puntoLlegada = new LatLng(-34.8164701, -58.5372424);


        googleMap.addMarker(new MarkerOptions()
                .position(puntoLlegada)
                .title("Llegada: París"));

        // Dibujamos la línea (Polyline)
        googleMap.addPolyline(new PolylineOptions()
                .add(puntoSalida, puntoLlegada) // Conecta los puntos
                .width(10f)           // Grosor de la línea
                .color(Color.RED)     // Color de la línea (Importar android.graphics.Color)
                .geodesic(true));     // True hace que la línea se curve como un vuelo real

        // D. Movemos la cámara para que se vean AMBOS puntos (Zoom automático)
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(puntoSalida);
        builder.include(puntoLlegada);
        LatLngBounds bounds = builder.build();

        // El '100' es el padding (margen) en píxeles para que los marcadores no toquen el borde
        int padding = 150;

        try {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
        } catch (Exception e) {
            // A veces falla si el mapa no ha terminado de calcular su tamaño en pantalla
            // Si eso pasa, usamos una vista por defecto al punto de salida
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoSalida, 5f));
        }
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
package es.iescarrillo.diseofigma;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat; // Importante para el vector
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class VueloFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

    public VueloFragment() {
    }

    public static VueloFragment newInstance() {
        return new VueloFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vuelo, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

        // 1. Configuración básica del mapa
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);

        // --- 2. AQUÍ ESTÁ LA LÓGICA DE LOS BOTONES (LO QUE FALTABA) ---
        // Usamos getView() para encontrar los botones dentro del Fragment
        View view = getView();
        if (view != null) {
            // Botón Más (+)
            view.findViewById(R.id.btnZoomIn).setOnClickListener(v -> {
                if (googleMap != null) {
                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                }
            });

            // Botón Menos (-)
            view.findViewById(R.id.btnZoomOut).setOnClickListener(v -> {
                if (googleMap != null) {
                    googleMap.animateCamera(CameraUpdateFactory.zoomOut());
                }
            });
        }
        // ---------------------------------------------------------------

        // 3. COORDENADAS
        LatLng madrid = new LatLng(40.472255, -3.560917);
        LatLng buenosAires = new LatLng(-34.8164701, -58.5372424);

        // 4. CALCULAR PUNTO INTERMEDIO EN LA CURVA (Para el avión)
        double porcentajeVuelo = 0.5; // 50% del trayecto
        LatLng posicionAvion = calcularPuntoIntermedio(madrid, buenosAires, porcentajeVuelo);

        // 5. DIBUJAR RUTA (Curva roja)
        googleMap.addPolyline(new PolylineOptions()
                .add(madrid, buenosAires)
                .width(8f)
                .color(Color.parseColor("#E53935")) // Rojo
                .geodesic(true));

        // 6. MARCADOR DESTINO
        googleMap.addMarker(new MarkerOptions().position(buenosAires).title("EZE"));

        // 7. AÑADIR EL AVIÓN
        // Calculamos la rotación para que el avión mire hacia el destino siguiendo la curva
        float rotacion = (float) calcularBearing(posicionAvion, buenosAires);

        googleMap.addMarker(new MarkerOptions()
                .position(posicionAvion)
                .icon(bitmapDescriptorFromVector(getContext(), R.drawable.ic_plane_marker))
                .rotation(rotacion)
                .anchor(0.5f, 0.5f) // Centrado para que rote bien
                .flat(true) // Pegado al mapa
                .title("Vuelo IB-6845"));

        // 8. MOVER LA CÁMARA
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(madrid);
        builder.include(buenosAires);

        try {
            // Movemos la cámara para que quepan los dos puntos con margen (150px)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
        } catch (Exception e) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicionAvion, 3f));
        }
    }

    /**
     * Calcula un punto exacto sobre la línea geodésica (curva) entre dos coordenadas.
     * @param fraction 0.0 es el inicio, 1.0 es el final, 0.5 es el medio.
     */
    private LatLng calcularPuntoIntermedio(LatLng origin, LatLng dest, double fraction) {
        double lat1 = Math.toRadians(origin.latitude);
        double lon1 = Math.toRadians(origin.longitude);
        double lat2 = Math.toRadians(dest.latitude);
        double lon2 = Math.toRadians(dest.longitude);

        // Distancia angular entre puntos
        double d = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((lat1 - lat2) / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon1 - lon2) / 2), 2)));

        // Fórmula de interpolación esférica (Slerp)
        double A = Math.sin((1 - fraction) * d) / Math.sin(d);
        double B = Math.sin(fraction * d) / Math.sin(d);

        double x = A * Math.cos(lat1) * Math.cos(lon1) + B * Math.cos(lat2) * Math.cos(lon2);
        double y = A * Math.cos(lat1) * Math.sin(lon1) + B * Math.cos(lat2) * Math.sin(lon2);
        double z = A * Math.sin(lat1) + B * Math.sin(lat2);

        double lat = Math.atan2(z, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        double lon = Math.atan2(y, x);

        return new LatLng(Math.toDegrees(lat), Math.toDegrees(lon));
    }

    // --- MÉTODOS AUXILIARES ---

    /**
     * Convierte un Vector XML (Drawable) a BitmapDescriptor para que Google Maps lo entienda.
     */
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        if (vectorDrawable == null) return null;

        // Aquí le puedes cambiar el color (Tint) si quieres que sea negro o azul
        vectorDrawable.setTint(Color.BLACK);

        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    /**
     * Calcula el ángulo (rumbo) entre dos coordenadas para rotar el icono correctamente.
     */
    private double calcularBearing(LatLng start, LatLng end) {
        double lat1 = Math.toRadians(start.latitude);
        double lon1 = Math.toRadians(start.longitude);
        double lat2 = Math.toRadians(end.latitude);
        double lon2 = Math.toRadians(end.longitude);

        double dLon = lon2 - lon1;

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon);

        double bearing = Math.toDegrees(Math.atan2(y, x));
        return (bearing + 360) % 360; // Normalizar a 0-360 grados
    }

    // Ciclo de vida obligatorio
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
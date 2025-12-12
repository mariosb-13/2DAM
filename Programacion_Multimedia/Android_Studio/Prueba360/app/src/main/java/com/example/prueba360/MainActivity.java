package com.example.prueba360;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // --- CONFIGURACIÓN ---
    private String urlBase;
    private String slugRecibido;

    // ⚠️ TE FALTABA ESTA VARIABLE (Sin ella, la app no sabe que son .jpg)
    private final String URL_SUFFIX = ".jpg?w=576&q=60&dpr=1&updated_at=1665692308&h=384";

    private final int TOTAL_IMAGENES = 36;
    private final float SENSIBILIDAD = 20f;

    // Variables de estado
    private int indiceActual = 1;
    private float ultimoX = 0f;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. RECIBIMOS EL SLUG (ID) DE LA ZAPA
        slugRecibido = getIntent().getStringExtra("SLUG_ZAPATILLA");

        // Fallback por si abres la app directamente sin pasar por el menú
        if (slugRecibido == null || slugRecibido.isEmpty()) {
            slugRecibido = "Air-Jordan-1-Retro-High-OG-Chicago-Reimagined";
        }

        // 2. CONSTRUIMOS LA URL
        urlBase = "https://images.stockx.com/360/" + slugRecibido + "/Images/" + slugRecibido + "/Lv2/img";

        imageView = findViewById(R.id.ivZapatilla360);

        // Cargamos
        cargarImagen(1);
        precargarResto();
        setupTouchListener();

        // ❌ HE BORRADO EL BUCLE INFINITO QUE TENÍAS AQUÍ ❌
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupTouchListener() {
        imageView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ultimoX = event.getX();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float actualX = event.getX();
                    float diferencia = actualX - ultimoX;
                    if (Math.abs(diferencia) > SENSIBILIDAD) {
                        if (diferencia > 0) decrementarIndice();
                        else incrementarIndice();
                        cargarImagen(indiceActual);
                        ultimoX = actualX;
                    }
                    return true;
                default:
                    return false;
            }
        });
    }

    private void incrementarIndice() {
        indiceActual++;
        if (indiceActual > TOTAL_IMAGENES) indiceActual = 1;
    }

    private void decrementarIndice() {
        indiceActual--;
        if (indiceActual < 1) indiceActual = TOTAL_IMAGENES;
    }

    private void cargarImagen(int indice) {
        String urlFinal = construirUrl(indice);
        Glide.with(this)
                .load(urlFinal)
                .placeholder(imageView.getDrawable())
                .error("https://images.stockx.com/images/" + slugRecibido + ".jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    private void precargarResto() {
        new Thread(() -> {
            for (int i = 1; i <= TOTAL_IMAGENES; i++) {
                final String url = construirUrl(i);
                runOnUiThread(() -> Glide.with(MainActivity.this)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(com.bumptech.glide.Priority.LOW)
                        .preload());
            }
        }).start();
    }

    private String construirUrl(int indice) {
        String numeroFormateado = String.format(Locale.US, "%02d", indice);
        return urlBase + numeroFormateado + URL_SUFFIX;
    }
}
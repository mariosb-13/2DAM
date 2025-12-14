package es.iescarrillo.diseofigma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout (asegúrate de que tu XML se llame fragment_main)
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Buscamos las tarjetas por su ID y les damos funcionalidad
        configurarBotonUrl(view, R.id.cardYoutube, "https://www.youtube.com");
        configurarBotonUrl(view, R.id.cardTiktok, "https://www.tiktok.com");
        configurarBotonUrl(view, R.id.cardSpotify, "https://open.spotify.com");
        configurarBotonUrl(view, R.id.cardHbo, "https://www.hbomax.com");
        configurarBotonUrl(view, R.id.cardDisney, "https://www.disneyplus.com");
        configurarBotonUrl(view, R.id.cardNetflix, "https://www.netflix.com");
        configurarBotonUrl(view, R.id.cardDazn, "https://www.dazn.com");
        configurarBotonUrl(view, R.id.cardApple, "https://tv.apple.com");
        configurarBotonUrl(view, R.id.cardTrivago, "https://www.trivago.es");
        configurarBotonUrl(view, R.id.cardAirbnb, "https://www.airbnb.es");

    }

    /**
     * Método auxiliar para asignar la acción de abrir URL a una CardView.
     * Esto ahorra escribir el mismo código 10 veces.
     */
    private void configurarBotonUrl(@NonNull View view, int cardId, String url) {
        CardView card = view.findViewById(cardId);
        if (card != null) {
            card.setOnClickListener(v -> {
                // Abre el navegador o la app si está instalada
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }
    }
}
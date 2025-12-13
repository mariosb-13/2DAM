package es.iescarrillo.diseofigma;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;

public class MainFragment extends Fragment {

    // 1. Declarar la variable DE LA URI y el LAUNCHER aquí arriba
    private Uri photoUri;
    private ActivityResultLauncher<Uri> takePictureLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. INICIALIZAR el launcher AQUÍ (en onCreate), NO en el botón
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                success -> {
                    if (success) {
                        // La foto se tomó correctamente y está en 'photoUri'
                        Toast.makeText(getContext(), "Foto guardada!", Toast.LENGTH_SHORT).show();
                        // Aquí podrías poner la foto en un ImageView si quisieras
                    }
                }
        );
    }



    // Método auxiliar para crear el archivo temporal y la URI
    private Uri crearUriParaFoto() {
        File imagePath = new File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "mis_fotos");
        if (!imagePath.exists()) imagePath.mkdirs();

        File newFile = new File(imagePath, "foto_vuelo_" + System.currentTimeMillis() + ".jpg");

        return FileProvider.getUriForFile(
                requireContext(),
                requireContext().getPackageName() + ".fileprovider",
                newFile
        );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Esta línea es la que conecta tu código Java con el diseño visual
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 3. En el botón (por ejemplo el de Duty Free que tiene icono de cámara)
        // Buscamos el CardView o botón que quieras usar
        View btnCamara = view.findViewById(R.id.iconCacahuete);

        if(btnCamara != null) {
            btnCamara.setOnClickListener(v -> {
                // Generamos la URI nueva
                photoUri = crearUriParaFoto();
                // Lanzamos la cámara
                takePictureLauncher.launch(photoUri);
            });
        }
    }
}
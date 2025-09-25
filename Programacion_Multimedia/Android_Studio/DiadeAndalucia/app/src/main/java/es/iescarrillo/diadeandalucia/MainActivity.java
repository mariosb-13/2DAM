package es.iescarrillo.diadeandalucia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {


    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            Button boton=(Button)findViewById(R.id.button);
            boton.setOnClickListener(this);


            mp=MediaPlayer.create(this, R.raw.himno_de_andalucia);
            return insets;

        });
    }

    @Override
    public void onClick(View v) {
    mp.start();
        Toast.makeText(getApplicationContext(), "Feliz Día de Andalucía", Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy(Bundle savedInstanceState) {
        mp.stop();
    }
}
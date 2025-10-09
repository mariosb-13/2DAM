package es.iescarrillo.myfirstappmsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonMensaje = (Button) findViewById(R.id.botonMensaje);
        buttonMensaje.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Boton siguiente que manda a la siguiente Activity
        if (findViewById(R.id.botonMensaje).isPressed()) {
            //Creamos un Intent para ir a la siguiente activity
            Intent viewNameIntent = new Intent(this, SecondActivity.class);

            //Inicia la Actividad
            startActivity(viewNameIntent);
        }
    }
}
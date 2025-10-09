package es.iescarrillo.myfirstapplication;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSpanish;
    Button buttonNext;
    Button buttonEnglish;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Intent viewMainIntent = getIntent();
        if (getIntent().getStringExtra("name")!= null){
            etName.setText("Tu nombre es " + viewMainIntent.getStringExtra("name"));
        }


        buttonNext = (Button) findViewById(R.id.button_Siguiente);
        buttonSpanish = (Button) findViewById(R.id.button_Spanish);
        buttonEnglish = (Button) findViewById(R.id.button_English);

        buttonNext.setOnClickListener(this);
        buttonSpanish.setOnClickListener(this);
        buttonEnglish.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public void onClick(View v) {
        etName = (EditText) findViewById(R.id.etName);
        TextView tvGrettings = (TextView) findViewById(R.id.infoNombre);

        if (etName.getText().toString().isEmpty()) {
            tvGrettings.setVisibility(VISIBLE);
            tvGrettings.setTextColor(Color.RED);
        } else {
            tvGrettings.setVisibility(VISIBLE);
            tvGrettings.setTextColor(Color.BLACK);
            if (findViewById(R.id.button_English).isPressed()) {
                tvGrettings.setText("Hello " + etName.getText().toString() + "!");
            } else if (findViewById(R.id.button_Spanish).isPressed()) {
                tvGrettings.setText("Hola " + etName.getText().toString() + "!");
            }
        }

        //Boton siguiente que manda a la siguiente Activity
        if (findViewById(R.id.button_Siguiente).isPressed()) {
            //Creamos un Intent para ir a la siguiente activity
            Intent viewNameIntent = new Intent(this, SecondActivity.class);

            //Añadir datos
            viewNameIntent.putExtra("name", etName.getText().toString());

            //Inicia la Actividad
            startActivity(viewNameIntent);
        }
    }
}
package es.iescarrillo.myfirstappmsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEnviarDatos;
    EditText tvTexto;
    EditText tvEntero;
    EditText tvDecimal;
    Switch switchBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnEnviarDatos = (Button) findViewById(R.id.buttonEnviarDatos);
        tvTexto = (EditText) findViewById(R.id.editText);
        tvEntero = (EditText) findViewById(R.id.editTextNumber);
        tvDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);
        switchBoolean = (Switch) findViewById(R.id.switchBoolean);

        btnEnviarDatos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Boton siguiente que manda a la ThirdActivity
        if (findViewById(R.id.buttonEnviarDatos).isPressed()) {
            //Creamos un Intent para ir a la siguiente activity
            Intent viewNameIntent = new Intent(this, ThirdActivity.class);

            viewNameIntent.putExtra("name", tvTexto.getText().toString());
            viewNameIntent.putExtra("numero", tvEntero.getText().toString());
            viewNameIntent.putExtra("numeroDecimal", tvDecimal.getText().toString());
            viewNameIntent.putExtra("boolean", switchBoolean.isChecked());

            //Inicia la Actividad
            startActivity(viewNameIntent);
        }

    }
}
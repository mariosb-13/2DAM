package es.iescarrillo.appcontador;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public int contador;
    TextView tvResultado;
    EditText etNum;
    String num;
    CheckBox checkBox;
    Button bntEnviar;

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

        tvResultado = findViewById(R.id.tvContadorNum);
        checkBox = findViewById(R.id.checkBox);
        etNum = findViewById(R.id.etNumber);
        bntEnviar = findViewById((R.id.buttonEnviarDatos));

        contador = 0;
    }

    /**
     * Método que incrementa el contador en 1
     *
     * @param vista
     */
    public void incrementaContador(View vista) {
        contador++;
        mostrarResultado();
    }

    /**
     * Método que decrementa el contador en 1
     *
     * @param vista
     */
    public void decrementaContador(View vista) {
        contador--;
        mostrarResultado();

        if (contador < 0) {
            if (!checkBox.isChecked()) {
                contador = 0;
                mostrarResultado();
            }
        }
    }

    /**
     * Método que establece el contado a 0
     *
     * @param vista
     */
    public void reseteaResultado(View vista) {
        contador = 0;
        num = String.valueOf(etNum.getText());

        if (num.isEmpty()) {
            mostrarResultado();
        } else {
            tvResultado.setVisibility(VISIBLE);
            tvResultado.setText("" + num);
        }

    }

    /**
     * Muestra el resultado del contador
     */
    public void mostrarResultado() {
        tvResultado.setText("" + contador);
        tvResultado.setVisibility(VISIBLE);
    }

    public void enviarDatos(View vista){
        //Boton siguiente que manda a la siguiente Activity
        if (findViewById(R.id.buttonEnviarDatos).isPressed()) {
            //Creamos un Intent para ir a la siguiente activity
            Intent viewNameIntent = new Intent(this, SecondActivity.class);

            viewNameIntent.putExtra("cont", tvResultado.getText().toString());
            viewNameIntent.putExtra("check", checkBox.isChecked());

            //Inicia la Actividad
            startActivity(viewNameIntent);
        }
    }
}
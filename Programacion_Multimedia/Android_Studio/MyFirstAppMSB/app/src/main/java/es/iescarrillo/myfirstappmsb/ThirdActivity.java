package es.iescarrillo.myfirstappmsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnVolver;
    TextView tvDatos, tvDatos2, tvDatos3, tvDatos4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDatos = findViewById(R.id.textViewDatos);
        tvDatos2 = findViewById(R.id.textViewDatos2);
        tvDatos3 = findViewById(R.id.textViewDatos3);
        tvDatos4 = findViewById(R.id.textViewDatos4);

        btnVolver = findViewById(R.id.buttonVolver);
        btnVolver.setOnClickListener(this);

        Intent viewMainIntent = getIntent();
        if (viewMainIntent != null) {
            tvDatos.setText("Texto: " + viewMainIntent.getStringExtra("name"));
            tvDatos2.setText("Número entero: " + viewMainIntent.getStringExtra("numero"));
            tvDatos3.setText("Número decimal: " + viewMainIntent.getStringExtra("numeroDecimal"));

            boolean estadoSwitch = viewMainIntent.getBooleanExtra("boolean", false);
            tvDatos4.setText("Switch activado: " + estadoSwitch);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonVolver) {
            Intent viewNameIntent = new Intent(this, MainActivity.class);
            startActivity(viewNameIntent);
        }
    }
}

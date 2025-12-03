package es.iescarrillo.diseofigma;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        TextView tvAyuda = findViewById(R.id.tvInfoCodVuelo);

        String textoCompleto = "Accede aquí para saber tu código de vuelo";
        String subCadena = "aquí";

        SpannableString ss = new SpannableString(textoCompleto);

        int inicio = textoCompleto.indexOf(subCadena);
        int fin = inicio + subCadena.length();

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);

        UnderlineSpan underlineSpan = new UnderlineSpan();

        ss.setSpan(colorSpan, inicio, fin, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(underlineSpan, inicio, fin, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvAyuda.setText(ss);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}
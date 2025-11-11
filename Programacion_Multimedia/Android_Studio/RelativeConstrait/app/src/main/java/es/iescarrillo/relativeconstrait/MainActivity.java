package es.iescarrillo.relativeconstrait;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity.java o MainActivity.kt
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrait_view); // <- tu ConstraintLayout aquí
    }
}

package es.iescarrillo.sqlite_ejercicioclase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTarea extends AppCompatActivity {

    String id;
    TextView tvId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarea);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvId = findViewById(R.id.tvIdTarea);
        tvId.setText(id);

    }
}
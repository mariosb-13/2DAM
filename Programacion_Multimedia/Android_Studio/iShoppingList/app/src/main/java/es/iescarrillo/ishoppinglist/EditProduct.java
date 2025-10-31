package es.iescarrillo.ishoppinglist;

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

public class EditProduct extends AppCompatActivity {


    public int id;
    public String name;
    public String note_info;
    public boolean state_buy;

    TextView tvId;
    EditText etTitle,etInfo;
    Switch switchStateBuy;
    Button btnCancel,btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Establecemos
        etTitle=findViewById(R.id.etTitle);
        tvId=findViewById(R.id.tvID2);
        etInfo=findViewById(R.id.etInfo);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);

        //Nos traemos los datos del activity anterior
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        note_info = intent.getStringExtra("note_info");
        state_buy = intent.getBooleanExtra("state_buy", false);



        etTitle.setText(name);
        tvId.setText(String.valueOf(id));
        etInfo.setText(String.valueOf(note_info));

    }

    public void returnActivity(View vista){
        Intent volverIntent = new Intent(this, MainActivity.class);
        startActivity(volverIntent);
    }

    public void saveProduct(View vista) {
        //Nos traemos los datos de los editText
        String newName = etTitle.getText().toString();
        String newNote = etInfo.getText().toString();
        boolean newState = switchStateBuy.isChecked();

        // Creamos un Intent para enviar los datos de vuelta
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", newName);
        resultIntent.putExtra("note_info", newNote);
        resultIntent.putExtra("state_buy", newState);

        setResult(RESULT_OK, resultIntent); // Indicamos que la operación fue exitosa
        finish(); // Cerramos EditProduct y volvemos a MainActivity
    }

}
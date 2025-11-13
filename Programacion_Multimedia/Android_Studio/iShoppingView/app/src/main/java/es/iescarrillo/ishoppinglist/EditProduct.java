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

    EditText etTitle, etInfo;
    Switch switchStateBuy;
    Button btnCancel, btnSave;

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

        etTitle = findViewById(R.id.etTitle);
        etInfo = findViewById(R.id.etInfo);
        switchStateBuy = findViewById(R.id.switchStateBuy);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        note_info = intent.getStringExtra("note_info");
        state_buy = intent.getBooleanExtra("state_buy", false);

        etTitle.setText(name);
        etInfo.setText(note_info);
        switchStateBuy.setChecked(state_buy);
    }

    public void saveProduct(View vista) {
        String newName = etTitle.getText().toString();
        String newNote = etInfo.getText().toString();
        boolean newState = switchStateBuy.isChecked();

        // Actualizamos el producto directamente
        for (Producto producto : MainActivity.listProducts) {
            if (producto.getId() == id) {
                producto.setName(newName);
                producto.setNote_info(newNote);
                producto.setState_buy(newState);
                break;
            }
        }

        // Volvemos al MainActivity
        Intent volverIntent = new Intent(this, MainActivity.class);
        startActivity(volverIntent);
        finish();
    }

    public void returnActivity(View vista) {
        Intent volverIntent = new Intent(this, MainActivity.class);
        startActivity(volverIntent);
        finish();
    }
}

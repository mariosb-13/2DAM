package es.iescarrillo.ishoppinglist;

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

public class AddProduct extends AppCompatActivity {

    public int id;
    public String title;
    public String note_info;
    public boolean state_buy;
    EditText etTitle, etId, etInfo;
    Switch switchStateBuy;
    Button btnCancel, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etAddTitle);
        etId = findViewById(R.id.etAddID);
        etInfo = findViewById(R.id.etAddInfo);
        switchStateBuy = findViewById(R.id.switchAddStatus);
        btnCancel = findViewById(R.id.btnAddCancel);
        btnAdd = findViewById(R.id.btnAddProduct);

    }

    /**
     * Metodo para añadir un producto a la lista
     * @param vista
     */
    public void addProduct(View vista) {
        //Extraemos los datos de los editText
        title = etTitle.getText().toString();
        id=Integer.parseInt(etId.getText().toString());
        note_info = etInfo.getText().toString();
        state_buy = switchStateBuy.isChecked();
        MainActivity.updateSpinner(this,id,title,note_info,state_buy);
        finish();

    }

    /**
     * Metodo para cancelar la accion
     * @param vista
     */
    public void cancel(View vista) {
        finish();
    }
}
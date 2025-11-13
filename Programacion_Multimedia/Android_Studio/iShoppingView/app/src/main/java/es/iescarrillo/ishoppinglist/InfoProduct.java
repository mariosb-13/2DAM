package es.iescarrillo.ishoppinglist;

import android.annotation.SuppressLint;
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

public class InfoProduct extends AppCompatActivity {

    public int id;
    public String name;
    public String note_info;
    public boolean state_buy;

    TextView tvTitle, tvInfo, tvID;
    Switch switchStateBuy;
    Button btnReturn, btnEdit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_product);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        note_info = intent.getStringExtra("note_info");
        state_buy = intent.getBooleanExtra("state_buy", false);

        tvTitle = findViewById(R.id.tvTitle);
        tvID = findViewById(R.id.tvInfo);
        tvInfo = findViewById(R.id.tvInfo);
        switchStateBuy = findViewById(R.id.switchStateBuy);
        btnReturn = findViewById(R.id.btnCancel);
        btnEdit = findViewById(R.id.btnEdit);

        tvTitle.setText(String.valueOf(name));
        tvID.setText("ID: " + id);
        tvInfo.setText("Info: " + note_info);
        switchStateBuy.setChecked(state_buy);

        // Evita que el usuario cambie el switch
        switchStateBuy.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchStateBuy.setChecked(state_buy);
        });
    }

    public void returnActivity(View vista) {
        Intent volverIntent = new Intent(this, MainActivity.class);
        startActivity(volverIntent);
        finish();
    }

    public void edit(View vista) {
        Intent editIntent = new Intent(this, EditProduct.class);
        editIntent.putExtra("id", id);
        editIntent.putExtra("name", name);
        editIntent.putExtra("note_info", note_info);
        editIntent.putExtra("state_buy", state_buy);
        startActivity(editIntent);
        finish();
    }

}

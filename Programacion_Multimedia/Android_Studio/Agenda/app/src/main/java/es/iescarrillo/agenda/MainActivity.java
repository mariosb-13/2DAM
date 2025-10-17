package es.iescarrillo.agenda;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnSearch, btnClear, btnRecord;
    EditText etFecha, etContent;

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

        btnClear = findViewById((R.id.btnClear));
        btnSearch= findViewById((R.id.btnSearch));

        etContent = findViewById(R.id.etContent);
        etFecha = findViewById(R.id.etFecha);
    }

    /**
     * Método que limpia el formulario
     */
    public void clear() {
        etFecha.setText("");
        etContent.setText("");
    }

    /**
     * Método que graba el nombre del fichero y su contenido
     * @param vista
     */
    public void grabar(View vista){
        String fecha= String.valueOf(etFecha.getText());
        String contenido= String.valueOf(etContent.getText());

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() + "/" + fecha.replace("/","-")));
            bw.write(contenido);
            Toast.makeText(getApplicationContext(), "Se ha creado el archivo correctamente", Toast.LENGTH_SHORT).show();
            clear();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que busca el nombre del fichero y te muestra por el editText el resultado
     * @param vista
     */
    public void buscar(View vista){
        String file= String.valueOf(etFecha.getText());

        try {
            BufferedReader br = new BufferedReader(new FileReader((getFilesDir() + "/" + file.replace("/","-"))));
            while (br.ready()){
                etContent.setText(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
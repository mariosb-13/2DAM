package es.iescarrillo.pruebaroom;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import es.iescarrillo.pruebaroom.entidades.Library;
import es.iescarrillo.pruebaroom.entidades.User;

public class MainActivity extends AppCompatActivity {

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
    }

    User u1 = new User("Mario",1001,22);
    User u2 = new User("Juan",1002,23);
    User u3 = new User("Pepe",1003,24);

    Library l1 = new Library(1,1001,"Biblioteca1");
    Library l2 = new Library(2,1002,"Biblioteca2");


}
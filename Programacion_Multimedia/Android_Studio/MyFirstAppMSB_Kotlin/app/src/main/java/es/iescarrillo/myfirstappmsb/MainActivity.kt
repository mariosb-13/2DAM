package es.iescarrillo.myfirstappmsb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var buttonMensaje: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        buttonMensaje = findViewById<View?>(R.id.botonMensaje) as Button
        buttonMensaje!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //Boton siguiente que manda a la siguiente Activity
        if (findViewById<View?>(R.id.botonMensaje).isPressed()) {
            //Creamos un Intent para ir a la siguiente activity
            val viewNameIntent = Intent(this, SecondActivity::class.java)

            //Inicia la Actividad
            startActivity(viewNameIntent)
        }
    }
}
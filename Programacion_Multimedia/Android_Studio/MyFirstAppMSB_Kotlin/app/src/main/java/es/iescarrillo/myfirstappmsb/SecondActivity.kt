package es.iescarrillo.myfirstappmsb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    var btnEnviarDatos: Button? = null
    var tvTexto: EditText? = null
    var tvEntero: EditText? = null
    var tvDecimal: EditText? = null
    var switchBoolean: Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })
        btnEnviarDatos = findViewById<View?>(R.id.buttonEnviarDatos) as Button
        tvTexto = findViewById<View?>(R.id.editText) as EditText
        tvEntero = findViewById<View?>(R.id.editTextNumber) as EditText
        tvDecimal = findViewById<View?>(R.id.editTextNumberDecimal) as EditText
        switchBoolean = findViewById<View?>(R.id.switchBoolean) as Switch

        btnEnviarDatos!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //Boton siguiente que manda a la ThirdActivity
        if (findViewById<View?>(R.id.buttonEnviarDatos).isPressed()) {
            //Creamos un Intent para ir a la siguiente activity
            val viewNameIntent = Intent(this, ThirdActivity::class.java)

            viewNameIntent.putExtra("name", tvTexto!!.getText().toString())
            viewNameIntent.putExtra("numero", tvEntero!!.getText().toString())
            viewNameIntent.putExtra("numeroDecimal", tvDecimal!!.getText().toString())
            viewNameIntent.putExtra("boolean", switchBoolean!!.isChecked())

            //Inicia la Actividad
            startActivity(viewNameIntent)
        }
    }
}
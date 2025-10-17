package es.iescarrillo.myfirstappmsb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity(), View.OnClickListener {
    var btnVolver: Button? = null
    var tvDatos: TextView? = null
    var tvDatos2: TextView? = null
    var tvDatos3: TextView? = null
    var tvDatos4: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        tvDatos = findViewById<TextView>(R.id.textViewDatos)
        tvDatos2 = findViewById<TextView>(R.id.textViewDatos2)
        tvDatos3 = findViewById<TextView>(R.id.textViewDatos3)
        tvDatos4 = findViewById<TextView>(R.id.textViewDatos4)

        btnVolver = findViewById<Button>(R.id.buttonVolver)
        btnVolver!!.setOnClickListener(this)

        val viewMainIntent = getIntent()
        if (viewMainIntent != null) {
            tvDatos!!.setText("Texto: " + viewMainIntent.getStringExtra("name"))
            tvDatos2!!.setText("Número entero: " + viewMainIntent.getStringExtra("numero"))
            tvDatos3!!.setText("Número decimal: " + viewMainIntent.getStringExtra("numeroDecimal"))

            val estadoSwitch = viewMainIntent.getBooleanExtra("boolean", false)
            tvDatos4!!.setText("Switch activado: " + estadoSwitch)
        }
    }

    override fun onClick(v: View) {
        if (v.getId() == R.id.buttonVolver) {
            val viewNameIntent = Intent(this, MainActivity::class.java)
            startActivity(viewNameIntent)
        }
    }
}

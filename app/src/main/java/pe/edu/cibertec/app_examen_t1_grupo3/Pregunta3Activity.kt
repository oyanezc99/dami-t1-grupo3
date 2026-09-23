package pe.edu.cibertec.app_examen_t1_grupo3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class Pregunta3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta3)


        val etDias = findViewById<EditText>(R.id.etDias)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)


        btnCalcular.setOnClickListener {
            val texto = etDias.text.toString().trim()


            if (texto.isEmpty()) {
                etDias.error = "Ingrese los días de retraso"
                tvResultado.text = "Aquí se mostrará el resultado"
                return@setOnClickListener
            }


            val dias = texto.toIntOrNull()
            if (dias == null || dias < 0) {
                etDias.error = "Ingrese un número entero válido (≥ 0)"
                tvResultado.text = "Aquí se mostrará el resultado"
                return@setOnClickListener
            }


            if (dias <= 3) {
                // Prórroga automática, sin multa
                tvResultado.text = "Préstamo regularizado dentro de la prórroga."
            } else {

                val diasSujetosCobro = dias - 3
                val multa = 12.00 + (3.50 * diasSujetosCobro)


                val multaFormateada = String.format(Locale.US, "S/ %.2f", multa)

                tvResultado.text = """
                    Días de demora: $dias
                    Días sujetos a cobro: $diasSujetosCobro
                    Multa administrativa a abonar: $multaFormateada
                """.trimIndent()
            }
        }
    }
}
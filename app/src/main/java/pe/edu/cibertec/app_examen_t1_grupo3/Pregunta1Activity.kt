package pe.edu.cibertec.app_examen_t1_grupo3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.app_examen_t1_grupo3.databinding.ActivityPregunta1Binding

class Pregunta1Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPregunta1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btncalcular.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btncalcular -> calcularPenalidadUI()
        }
    }

    private fun calcularPenalidad(diasRetraso: Int): Double {
        return if (diasRetraso <= 5) {
            0.0
        } else {
            val diasComputables = diasRetraso - 5
            500.0 + (diasComputables * 150.0)
        }
    }

    private fun calcularPenalidadUI() {
        val textoDias = binding.etdias.text.toString()
        if (textoDias.isEmpty()) {
            binding.tvresultado.text = "Por favor, ingrese los días de retraso."
            return
        }

        val diasRetraso = textoDias.toInt()

        if (diasRetraso <= 5) {
            binding.tvresultado.text = "Entrega dentro de la tolerancia contractual."
        } else {
            val diasComputables = diasRetraso - 5
            val penalidad = calcularPenalidad(diasRetraso)
            val penalidadFormateada = String.format("S/ %.2f", penalidad)

            binding.tvresultado.text = """
                Días de retraso: $diasRetraso
                Días computables para penalidad: $diasComputables
                Penalidad resultante: $penalidadFormateada
            """.trimIndent()
        }
    }
}
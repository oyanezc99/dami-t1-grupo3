package pe.edu.cibertec.app_examen_t1_grupo3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.app_examen_t1_grupo3.R

import pe.edu.cibertec.app_examen_t1_grupo3.databinding.ActivityPregunta4Binding

class Pregunta4Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Asignamos el evento al botón de cálculo
        binding.btncalcular.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btncalcular -> calcularSobregiro()
        }
    }

    // Función que calcula la comisión según las reglas del banco
    private fun calcularComision(monto: Double): Double {
        return if (monto <= 5000.0) {
            0.0
        } else {
            val exceso = monto - 5000.0
            150.0 + (exceso * 0.03)
        }
    }

    // Función principal que coordina el cálculo y la visualización en la interfaz
    private fun calcularSobregiro() {
        // Validamos que el campo no esté vacío para evitar errores
        val textoMonto = binding.etsoles.text.toString() // Asumiendo que usas etpeso o etmonto
        if (textoMonto.isEmpty()) {
            binding.tvresultado.text = "Por favor, ingrese el monto del sobregiro."
            return
        }

        val montoSobregiro = textoMonto.toDouble()
        val comision = calcularComision(montoSobregiro)

        if (montoSobregiro <= 5000.0) {
            binding.tvresultado.text = "Sobregiro protegido por línea preferente."
        } else {
            val exceso = montoSobregiro - 5000.0
            val excesoFormateado = String.format("S/ %.2f", exceso)
            val comisionFormateada = String.format("S/ %.2f", comision)
            val montoFormateado = String.format("S/ %.2f", montoSobregiro)

            binding.tvresultado.text = """
                Sobregiro solicitado: $montoFormateado
                Exceso del límite: $excesoFormateado
                Comisión total aplicada: $comisionFormateada
            """.trimIndent()
        }
    }
}
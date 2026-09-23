package pe.edu.cibertec.app_examen_t1_grupo3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.app_examen_t1_grupo3.databinding.ActivityPregunta6Binding
import java.util.Locale

class Pregunta6Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.btnCalcular.id) {
            calcularFacturacionSoporte()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calcularFacturacionSoporte() {
        val textoHoras = binding.etHoras.text.toString().trim()

        if (textoHoras.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese las horas consumidas", Toast.LENGTH_SHORT).show()
            return
        }

        val horas = textoHoras.toIntOrNull()
        if (horas == null || horas < 0) {
            Toast.makeText(this, "Ingrese una cantidad válida de horas", Toast.LENGTH_SHORT).show()
            return
        }

        if (horas <= 40) {
            binding.tvResultado.text = "Consumo cubierto por la póliza mensual contratada."
        } else {
            val horasExcedentes = horas - 40
            val facturacionComplementaria = 300.0 + (horasExcedentes * 85.0)

            val mensaje = """
                Horas totales consumidas: $horas h
                Horas excedentes: $horasExcedentes h
                Facturación complementaria: ${String.format(Locale.US, "S/ %.2f", facturacionComplementaria)}
            """.trimIndent()

            binding.tvResultado.text = mensaje
        }
    }
}
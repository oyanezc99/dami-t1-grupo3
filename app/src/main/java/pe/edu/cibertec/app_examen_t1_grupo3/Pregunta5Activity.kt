package pe.edu.cibertec.app_examen_t1_grupo5

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.app_examen_t1_grupo3.R
import pe.edu.cibertec.app_examen_t1_grupo3.databinding.ActivityPregunta5Binding

class Pregunta5Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPregunta5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btncalcular.setOnClickListener(this)
    }

    fun calExcesoD(decibelios: Double): Double {
        return decibelios - 55
    }

    fun calMulta(exceso: Double): Double {
        return 1200.00 + (180.00 * exceso)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btncalcular -> evaluarNS()
        }
    }
    fun evaluarNS() {
        val decibelios = binding.etdecibelios.text.toString().toDouble()

        if (decibelios <= 55) {
            binding.tvresultado.text = "Nivel sonoro conforme a la ordenanza."
        } else {
            val exceso = calExcesoD(decibelios)
            val sancion = calMulta(exceso)
            val sancionFormateada = String.format("%.2f", sancion)
            binding.tvresultado.text = "Decibelios medidos: $decibelios dB\n" + "Exceso sonoro: $exceso dB\n" +
                    "Monto de la sanción municipal: S/ $sancionFormateada"
        }
    }
}
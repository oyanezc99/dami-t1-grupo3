package pe.edu.cibertec.app_examen_t1_grupo3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.app_examen_t1_grupo3.databinding.ActivityPregunta7Binding
import java.util.Locale

class Pregunta7Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPregunta7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta7Binding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.question_7_title)
        binding.calculatePenalty.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.calculate_penalty) {
            calcularPenalidad()
        }
    }

    private fun calcularPenalidad() {
        val days = binding.daysLateInput.text.toString().toIntOrNull()
        if (days == null || days < 0) {
            binding.daysLateInput.error = getString(R.string.days_input_error)
            binding.question7Result.visibility = View.GONE
            return
        }

        binding.question7Result.text = if (days <= 2) {
            getString(R.string.no_penalty)
        } else {
            val excess = days - 2
            val penalty = 35 + 20 * excess
            getString(
                R.string.penalty_result,
                days,
                excess,
                String.format(Locale.US, "S/ %.2f", penalty.toDouble()),
            )
        }
        binding.question7Result.visibility = View.VISIBLE
    }
}

package pe.edu.cibertec.app_examen_t1_grupo3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.app_examen_t1_grupo3.R

class Pregunta2Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        findViewById<Button>(R.id.btnCalcular).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.btnCalcular -> {

                val prendas = findViewById<EditText>(R.id.etPrendas)
                    .text.toString()
                    .toIntOrNull() ?: 0

                val resultado = findViewById<TextView>(R.id.txtResultado)

                if (prendas <= 10) {

                    resultado.text =
                        "Nivel de merma dentro del margen admisible."

                } else {

                    val exceso = prendas - 10

                    val descuento = 100 + (exceso * 28)

                    resultado.text =
                        "Fallas registradas: $prendas\n" +
                                "Exceso de prendas defectuosas: $exceso\n" +
                                "Descuento total por reposición: S/ %.2f"
                                    .format(descuento.toDouble())
                }
            }
        }
    }
}
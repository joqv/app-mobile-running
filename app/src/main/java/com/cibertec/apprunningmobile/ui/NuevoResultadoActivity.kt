package com.cibertec.apprunningmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.database.ResultadoDBHelper
import com.cibertec.apprunningmobile.models.Resultado


class NuevoResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_resultado)

        val etNuevaDistancia1 = findViewById<EditText>(R.id.etNuevaDistancia)
        val etNuevaTiempo1 = findViewById<EditText>(R.id.etNuevoTiempo)
        val btnCalcularVelocidad = findViewById<Button>(R.id.btnCalcularVelocidad1)
        val etNuevaVelocidad = findViewById<EditText>(R.id.etNuevaVelocidad)
        val btnGuardarResultado = findViewById<Button>(R.id.btnGuardarResultado)

        var velocidad: Double? = null

        btnCalcularVelocidad.setOnClickListener{

            val distanciaStr = etNuevaDistancia1.text.toString()
            val tiempoStr = etNuevaTiempo1.text.toString()

            if (distanciaStr.isNotEmpty() && tiempoStr.isNotEmpty()) {
                val distancia = distanciaStr.toDouble()
                val tiempo = tiempoStr.toDouble()
                if (tiempo != 0.0) {
                    velocidad = distancia * 60 / tiempo
                    //btnCalcularVelocidad.text = velocidad.toString()
                } else {
                    //btnCalcularVelocidad.text = "Tempo inválido"
                }
            } else {
                //btnCalcularVelocidad.text = "Campos vazios"
            }

            etNuevaVelocidad.setText(velocidad.toString())
        }

        btnGuardarResultado.setOnClickListener {

            val nombre = findViewById<EditText>(R.id.etNuevoNombreResultado).text.toString()
            val distancia = findViewById<EditText>(R.id.etNuevaDistancia).text.toString().toDoubleOrNull()
            val tiempo = findViewById<EditText>(R.id.etNuevoTiempo).text.toString().toDoubleOrNull()
            val velocidad = findViewById<EditText>(R.id.etNuevaVelocidad).text.toString().toDoubleOrNull()


            if (!nombre.isNullOrEmpty() && distancia != null && tiempo != null && velocidad != null) {
                val resultado = Resultado(nombre, distancia, tiempo, velocidad)
                val dbHelper = ResultadoDBHelper(this)
                dbHelper.insertarResultado(resultado)

                Toast.makeText(this, "Resultado guardado exitosamente", Toast.LENGTH_SHORT).show()
                 // Cierra la actividad si quieres
            } else {
                Toast.makeText(this, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }

        }

        val buttonAtletasRes = findViewById<Button>(R.id.btnVolverListaResultado)

        buttonAtletasRes.setOnClickListener {
            finish()
        }
    }
}

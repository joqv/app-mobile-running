package com.cibertec.apprunningmobile

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView

class MainActivityPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_principal)

        val imagenPrincipal = findViewById<ImageView>(R.id.imageViewPrincipalWing)
        imagenPrincipal.setImageResource(R.drawable.pngwing)

        // Inicio código Jose para desarrollo
        val buttonAtletasRest = findViewById<Button>(R.id.buttonResultadoEventoDesarrollo)

        buttonAtletasRest.setOnClickListener {
            val intentAtletasRest = Intent(this, ActivityResultadosEventos::class.java)
            startActivity(intentAtletasRest)
        }
        // Fin código Jose para desarrollo

        // codigo Omar
      //  val btnResultados = findViewById<Button>(R.id.buttonResultadoEventoDesarrollo)
       // btnResultados.setOnClickListener {
         //   val intent = Intent(this, NuevoResultadoActivity::class.java)
         //   startActivity(intent)
        //}


        //fin codigo Omar
    }
}

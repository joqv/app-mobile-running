package com.cibertec.apprunningmobile

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import com.cibertec.apprunningmobile.ui.ActivityApiResultadosEventos
import com.cibertec.apprunningmobile.ui.ActivityResultadosPersonal

class MainActivityPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_principal)

        val imagenPrincipal = findViewById<ImageView>(R.id.imageViewPrincipalWing)
        imagenPrincipal.setImageResource(R.drawable.pngwing)




        // Inicio código Jose para desarrollo
        val buttonAtletasRest = findViewById<Button>(R.id.buttonDesarrolloJose)

        buttonAtletasRest.setOnClickListener {
            val intentAtletasRest = Intent(this, ActivityApiResultadosEventos::class.java)
            startActivity(intentAtletasRest)
        }
        // Fin código Jose para desarrollo



        // Codigo Omar
        val buttonOmar = findViewById<Button>(R.id.buttonDesarrolloOmar)

        buttonOmar.setOnClickListener {
            val intent = Intent(this, ActivityResultadosPersonal::class.java)
            startActivity(intent)
        }
        // Fin codigo Omar




        // Codigo Julio
       val buttonJulio = findViewById<Button>(R.id.buttonDesarrolloJulio)

       buttonJulio.setOnClickListener {
          val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        }
        // Fin codigo Julio
   }
}

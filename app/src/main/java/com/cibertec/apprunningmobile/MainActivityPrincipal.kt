package com.cibertec.apprunningmobile

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import com.cibertec.apprunningmobile.ui.ActivityApiResultadosEventos
import com.cibertec.apprunningmobile.ui.ActivityHome
import com.cibertec.apprunningmobile.ui.ActivityResultadosPersonal
import com.cibertec.apprunningmobile.ui.LoginActivity

class MainActivityPrincipal : AppCompatActivity() {


    // Declara el NUEVO botón del clima
    private lateinit var btnWeather: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_principal)

        val buttonAccederPrincipal = findViewById<Button>(R.id.buttonAccederPrincipal)

        buttonAccederPrincipal.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
   }
}

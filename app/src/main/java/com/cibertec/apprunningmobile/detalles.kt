package com.cibertec.apprunningmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class detalles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles)

        val btnVolverDetallesMenu = findViewById<Button>(R.id.volverDetallesMenu)

        btnVolverDetallesMenu.setOnClickListener {

            val intent = Intent(this,MainActivityPrincipal::class.java)
            startActivity(intent)

        }

        }
    }

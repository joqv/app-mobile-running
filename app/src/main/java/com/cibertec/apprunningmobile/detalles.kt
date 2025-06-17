package com.cibertec.apprunningmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles)

        val btnVolverDetallesMenu = findViewById<Button>(R.id.volverDetallesMenu)

        btnVolverDetallesMenu.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

        }
    }

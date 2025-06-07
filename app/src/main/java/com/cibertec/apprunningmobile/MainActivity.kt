package com.cibertec.apprunningmobile

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.cibertec.apprunningmobile.R.id.irDetalles

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnIrDetalles = findViewById<Button>(R.id.irDetalles)

        btnIrDetalles.setOnClickListener {

            val intent2 = Intent(this,detalles::class.java)
            startActivity(intent2)

        }

        }
    }

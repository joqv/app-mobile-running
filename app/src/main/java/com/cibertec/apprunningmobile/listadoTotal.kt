package com.cibertec.apprunningmobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class listadoTotal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_total)

        data class Corredor(
            val id: Int,
            val nomCorredor: String,
            val categoria: String,
            val fechaInicio: String,
            val horaInicio: String,
            val fechaTermino: String,
            val horaTermino: String
        )

        val listaCorredores = listOf(
            Corredor(1, "Juan Pérez", "Senior", "2025-06-01", "08:00", "2025-06-01", "10:30"),
            Corredor(2, "María López", "Junior", "2025-06-01", "08:15", "2025-06-01", "10:45"),
            Corredor(3, "Carlos Ruiz", "Senior", "2025-06-01", "08:30", "2025-06-01", "11:00")
        )

    }
}
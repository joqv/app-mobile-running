package com.cibertec.apprunningmobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.adapter.ResultadoApiAdapter

class ActivityResultadosEventos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resultadoApiAdapter: ResultadoApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados_eventos)




    }
}
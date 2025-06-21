package com.cibertec.apprunningmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R.id.btnNuevo
import com.cibertec.apprunningmobile.adapter.ResultadoApiAdapter

class ActivityResultadosEventos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resultadoApiAdapter: ResultadoApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados_eventos)

        val btnNuevo1 = findViewById<Button>(btnNuevo)
        btnNuevo1.setOnClickListener {
        val intent = Intent(this, NuevoResultadoActivity::class.java)
         startActivity(intent)
        }

        recyclerView = findViewById(R.id.RecyclerViewResultadosEventos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbHelper = ResultadoDatabaseHelper(this)
        val listaDeResultados = dbHelper.obtenerTodosLosResultados()

        resultadoApiAdapter = ResultadoApiAdapter(listaDeResultados)
        recyclerView.adapter = resultadoApiAdapter

    }



}
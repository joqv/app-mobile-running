package com.cibertec.apprunningmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.R.id.btnNuevo
import com.cibertec.apprunningmobile.adapter.ResultadoAdapter
import com.cibertec.apprunningmobile.database.ResultadoDBHelper

class ActivityResultadosPersonal : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resultadoApiAdapter: ResultadoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados_personal)

        val btnNuevo1 = findViewById<Button>(btnNuevo)
        btnNuevo1.setOnClickListener {
        val intent = Intent(this, NuevoResultadoActivity::class.java)
         startActivity(intent)
        }

        recyclerView = findViewById(R.id.RecyclerViewResultadosEventos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbHelper = ResultadoDBHelper(this)
        val listaDeResultados = dbHelper.obtenerTodosLosResultados()

        resultadoApiAdapter = ResultadoAdapter(listaDeResultados)
        recyclerView.adapter = resultadoApiAdapter

        val buttonVolver = findViewById<Button>(R.id.buttonResultadosPersonalesVolver)

        buttonVolver.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.RecyclerViewResultadosEventos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbHelper = ResultadoDBHelper(this)
        val listaDeResultados = dbHelper.obtenerTodosLosResultados()

        resultadoApiAdapter = ResultadoAdapter(listaDeResultados)
        recyclerView.adapter = resultadoApiAdapter

    }
}
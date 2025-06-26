package com.cibertec.apprunningmobile.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.adapter.ResultadoApiAdapter
import com.cibertec.apprunningmobile.models.ResultadoApi
import com.cibertec.apprunningmobile.network.ResultadoApiService
import com.cibertec.apprunningmobile.network.RunningRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityApiResultadosEventos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resultadoApiAdapter: ResultadoApiAdapter
    private lateinit var progressBarApiResultado: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_resultados_eventos)

        val buttonAtrasMaratonesEventos: LinearLayout = findViewById(R.id.linearLayoutAtrasMaratonesEventosButton)
        val eventoId = intent.getIntExtra("idEvento", 0)
        val tituloEvento = findViewById<TextView>(R.id.textViewTituloEvento)
        val nombreEvento = intent.getStringExtra("nombreEvento")

        tituloEvento.setText(nombreEvento)

        recyclerView = findViewById(R.id.reciclerViewResultadosEventos)
        recyclerView.visibility = View.GONE
        progressBarApiResultado = findViewById(R.id.progressBarApiResultados)
        progressBarApiResultado.visibility = View.VISIBLE

        RunningRetrofitClient.instance.getResultadosEventosApi(eventoId).enqueue(object : Callback<List<ResultadoApi>> {
            override fun onResponse(
                call: Call<List<ResultadoApi>>,
                response: Response<List<ResultadoApi>>
            ) {
                progressBarApiResultado.visibility = View.GONE

                if (response.isSuccessful) {
                    val resultados = response.body()
                    print("--resultados: "+ resultados)
                    resultados?.let {

                        recyclerView.layoutManager = LinearLayoutManager(this@ActivityApiResultadosEventos)
                        resultadoApiAdapter = ResultadoApiAdapter(resultados)
                        recyclerView.adapter = resultadoApiAdapter
                        recyclerView.visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(this@ActivityApiResultadosEventos, "Error al obtener datos: " + response.code(), Toast.LENGTH_SHORT).show()
                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<ResultadoApi>>, t: Throwable) {
                Toast.makeText(this@ActivityApiResultadosEventos, "Error al obtener datos.", Toast.LENGTH_SHORT).show()
            }
        })

        buttonAtrasMaratonesEventos.setOnClickListener {
            finish()
        }
    }
}
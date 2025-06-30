package com.cibertec.apprunningmobile.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
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
import com.cibertec.apprunningmobile.network.RunningRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityApiResultadosEventos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resultadoApiAdapter: ResultadoApiAdapter
    private lateinit var progressBarApiResultado: ProgressBar
    private lateinit var sinResultados: TextView
    private lateinit var contadorAtletas: TextView
    private lateinit var labelContadorAtletas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_resultados_eventos)

        val buttonAtrasMaratonesEventos: LinearLayout = findViewById(R.id.linearLayoutAtrasMaratonesEventosButton)
        val eventoId = intent.getIntExtra("idEvento", 0)
        val tituloEvento = findViewById<TextView>(R.id.textViewTituloEvento)
        val nombreEvento = intent.getStringExtra("nombreEvento")
        val consultaCorredorNombre: EditText = findViewById(R.id.editTextBuscadorCorredorNombre)
        val buttonBuscarCorredorNombre: ImageButton = findViewById(R.id.imageButtonBuscarCorredorNombre)


        tituloEvento.text = nombreEvento
        var nombre = consultaCorredorNombre.text.toString()

        recyclerView = findViewById(R.id.reciclerViewResultadosEventos)
        progressBarApiResultado = findViewById(R.id.progressBarApiResultados)
        sinResultados = findViewById(R.id.textViewNoHayResultadosCorredores)
        contadorAtletas = findViewById(R.id.textViewContadorAtletas)
        labelContadorAtletas = findViewById(R.id.labelContadorAtletas)

        recyclerView.visibility = View.GONE
        sinResultados.visibility = View.GONE
        progressBarApiResultado.visibility = View.VISIBLE
        contadorAtletas.visibility = View.GONE
        labelContadorAtletas.visibility = View.GONE

        obtenerDatosCorredores(eventoId, nombre)

        buttonAtrasMaratonesEventos.setOnClickListener {
            finish()
        }

        buttonBuscarCorredorNombre.setOnClickListener {
            progressBarApiResultado.visibility = View.VISIBLE
            recyclerView.visibility  = View.GONE
            sinResultados.visibility = View.GONE
            contadorAtletas.visibility = View.GONE
            labelContadorAtletas.visibility = View.GONE
            nombre = consultaCorredorNombre.text.toString()
            obtenerDatosCorredores(eventoId, nombre)
        }
    }

    private fun obtenerDatosCorredores(id: Int, nombre: String) {

        RunningRetrofitClient.resultadoApiService.getResultadosEventosApi(id, nombre).enqueue(object : Callback<List<ResultadoApi>> {

            override fun onResponse(
                call: Call<List<ResultadoApi>>,
                response: Response<List<ResultadoApi>>
            ) {
                progressBarApiResultado.visibility = View.GONE

                if (response.isSuccessful) {
                    val resultados = response.body()
                    print("--resultados: "+ resultados)
                    resultados?.let {

                        if (it.isEmpty()) {
                            sinResultados.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        } else {
                            recyclerView.layoutManager = LinearLayoutManager(this@ActivityApiResultadosEventos)
                            resultadoApiAdapter = ResultadoApiAdapter(resultados)
                            recyclerView.adapter = resultadoApiAdapter
                            contadorAtletas.text = resultados.size.toString()
                            contadorAtletas.visibility = View.VISIBLE
                            labelContadorAtletas.visibility = View.VISIBLE
                            recyclerView.visibility = View.VISIBLE
                            sinResultados.visibility = View.GONE
                        }
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
    }
}
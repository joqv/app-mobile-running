package com.cibertec.apprunningmobile.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.adapter.EventoApiAdapter
import com.cibertec.apprunningmobile.models.EventoApi
import com.cibertec.apprunningmobile.network.RunningRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityApiEventosLista : AppCompatActivity(), EventoApiAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventoApiAdapter: EventoApiAdapter
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_eventos_lista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonAtrasEventos: LinearLayout = findViewById(R.id.linearLayoutAtrasEventosApiButton)

        recyclerView = findViewById(R.id.reciclerViewEventosApiLista)
        progressBar = findViewById(R.id.progressBarEventosApiLista)

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility  = View.GONE

        RunningRetrofitClient.instance.getEventosApi().enqueue(object : Callback<List<EventoApi>> {

            override fun onResponse(
                call: Call<List<EventoApi>>,
                response: Response<List<EventoApi>>
            ) {
                progressBar.visibility = View.GONE

                if(response.isSuccessful) {
                    val eventos = response.body()
                    print("eventos: "+ eventos)
                    eventos?.let {

                        recyclerView.layoutManager = LinearLayoutManager(this@ActivityApiEventosLista)
                        eventoApiAdapter = EventoApiAdapter(eventos, this@ActivityApiEventosLista)
                        recyclerView.adapter = eventoApiAdapter
                        recyclerView.visibility = View.VISIBLE

                    }
                } else {
                    Toast.makeText(this@ActivityApiEventosLista, "Error al obtener datos: " + response.code(), Toast.LENGTH_SHORT).show()
                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<EventoApi>>, t: Throwable) {
                Toast.makeText(this@ActivityApiEventosLista, "Error al obtener datos: ", Toast.LENGTH_SHORT).show()
            }
        })

        buttonAtrasEventos.setOnClickListener {
            finish()
        }
    }

    override fun onItemClick(item: EventoApi) {

        val intent = Intent(this, ActivityApiResultadosEventos::class.java).apply {

            println("--idEvento: "+ item.idEvento)
            putExtra("idEvento", item.idEvento)
            putExtra("nombreEvento", item.nombreEvento)
        }
        startActivity(intent)

        println("click en item: " + item.idEvento)
    }
}
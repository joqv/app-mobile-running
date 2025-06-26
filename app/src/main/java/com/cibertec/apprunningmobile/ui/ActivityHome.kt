package com.cibertec.apprunningmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.adapter.ResultadoAdapter
import com.cibertec.apprunningmobile.adapter.ResultadoHomeAdapter
import com.cibertec.apprunningmobile.database.ResultadoDBHelper
import com.cibertec.apprunningmobile.models.Resultado

class ActivityHome : AppCompatActivity() {

    private lateinit var resultadoDBHelper: ResultadoDBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var resultatoHomeAdapter: ResultadoHomeAdapter
    private var resultados = mutableListOf<Resultado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ActivityHome)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultadoDBHelper = ResultadoDBHelper(this)

        actualizarDataResultadosPersonales()

        val buttonMaratonesEventos: LinearLayout = findViewById(R.id.linearLayoutMaratonesEventosButton)
        val buttonResultadosPersonales: LinearLayout = findViewById(R.id.linearLayoutResultadosPersonalesButton)
        val buttonGestionarUsuarios: LinearLayout = findViewById(R.id.linearLayoutGestionarUsuarioButton)


        buttonMaratonesEventos.setOnClickListener {
            val intentEventosLista = Intent(this, ActivityApiEventosLista::class.java)
            startActivity(intentEventosLista)
        }

        buttonResultadosPersonales.setOnClickListener {
            val intentResultadosPersonales = Intent(this, ActivityResultadosPersonal::class.java)
            startActivity(intentResultadosPersonales)
        }

        buttonGestionarUsuarios.setOnClickListener {
            val intentGestionarUsuarios = Intent(this, UserManagementActivity::class.java)
            startActivity(intentGestionarUsuarios)
        }
    }

    override fun onResume() {
        super.onResume()

        actualizarDataResultadosPersonales()
    }

    private fun actualizarDataResultadosPersonales() {
        resultados = resultadoDBHelper.obtenerTodosLosResultados().toMutableList()
        recyclerView = findViewById(R.id.recyclerViewHomeResultados)
        recyclerView.layoutManager = LinearLayoutManager(this)
        resultatoHomeAdapter = ResultadoHomeAdapter(resultados)
        recyclerView.adapter = resultatoHomeAdapter
    }
}
package com.cibertec.apprunningmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.models.ResultadoApi

class ResultadoApiAdapter(private val lista: List<ResultadoApi>): RecyclerView.Adapter<ResultadoApiAdapter.ResultadoApiViewHolder>() {

    class ResultadoApiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val nombre: TextView = itemView.findViewById(R.id.textViewNombreApi)
        val apellido: TextView = itemView.findViewById(R.id.textViewApellidoApi)
        val dorsal: TextView = itemView.findViewById(R.id.textViewDorsalApi)
        val pais: TextView = itemView.findViewById(R.id.textViewPaisApi)
        val edad: TextView = itemView.findViewById(R.id.textViewEdadApi)
        val puesto: TextView = itemView.findViewById(R.id.textViewNumeroPuestoApi)
        val tiempo: TextView = itemView.findViewById(R.id.textViewTiempoApi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultadoApiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_api_resultado_evento, parent,false)
        return ResultadoApiViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultadoApiViewHolder, position: Int) {
        val resultadoApi = lista[position]
        holder.nombre.text = resultadoApi.atleta?.nombre ?:"N/A"
        holder.apellido.text = resultadoApi.atleta?.apellido ?: "N/A"
        holder.dorsal.text = resultadoApi.dorsal ?: "N/A"
        holder.pais.text = resultadoApi.atleta?.pais?.nombrePais ?: "N/A"
        holder.edad.text = resultadoApi.atleta?.edad?.toString() ?: "N/A"
        holder.puesto.text = resultadoApi.puesto?.toString() ?: "N/A"
        holder.tiempo.text = resultadoApi.tiempo ?: "N/A"
    }

    override fun getItemCount(): Int = lista.size
}
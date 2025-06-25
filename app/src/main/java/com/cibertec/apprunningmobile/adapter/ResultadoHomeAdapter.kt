package com.cibertec.apprunningmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.models.Resultado

class ResultadoHomeAdapter(private val lista: List<Resultado>) :
    RecyclerView.Adapter<ResultadoHomeAdapter.ResultadoHomeViewHolder>() {

    class ResultadoHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textViewHomeDataDescripcion)
        val distancia: TextView = itemView.findViewById(R.id.textViewHomeDataDistancia)
        val tiempo: TextView = itemView.findViewById(R.id.textViewHomeDataTiempo)
        val velocidad: TextView = itemView.findViewById(R.id.textViewHomeDataRitmo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultadoHomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_resultado, parent, false)
        return ResultadoHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultadoHomeViewHolder, position: Int) {
        val resultado = lista[position]
        holder.nombre.text = resultado.nombre
        holder.distancia.text = String.format("%.2f km", resultado.distancia)
        holder.tiempo.text = String.format("%.2f min", resultado.tiempo)
        holder.velocidad.text = String.format("%.2f km/h", resultado.velocidad)
    }

    override fun getItemCount(): Int = lista.size
}
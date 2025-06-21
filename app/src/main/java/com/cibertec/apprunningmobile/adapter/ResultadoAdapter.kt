package com.cibertec.apprunningmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.models.Resultado



class ResultadoAdapter(private val lista: List<Resultado>) :
    RecyclerView.Adapter<ResultadoAdapter.ResultadoApiViewHolder>() {

    class ResultadoApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val distancia: TextView = itemView.findViewById(R.id.tvDistancia)
        val tiempo: TextView = itemView.findViewById(R.id.tvTiempo)
        val velocidad: TextView = itemView.findViewById(R.id.tvVelocidad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultadoApiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resultado_even, parent, false)
        return ResultadoApiViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultadoApiViewHolder, position: Int) {
        val resultado = lista[position]
        holder.nombre.text = resultado.nombre
        holder.distancia.text = String.format("%.2f km", resultado.distancia)
        holder.tiempo.text = String.format("%.2f min", resultado.tiempo)
        holder.velocidad.text = String.format("%.2f km/h", resultado.velocidad)
    }

    override fun getItemCount(): Int = lista.size
}
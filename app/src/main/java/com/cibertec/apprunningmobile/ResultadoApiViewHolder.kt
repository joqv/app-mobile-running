package com.cibertec.apprunningmobile

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultadoApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nombre: TextView = itemView.findViewById(R.id.tvNombre)
    val distancia: TextView = itemView.findViewById(R.id.tvDistancia)
    val tiempo: TextView = itemView.findViewById(R.id.tvTiempo)
    val velocidad: TextView = itemView.findViewById(R.id.tvVelocidad)
}

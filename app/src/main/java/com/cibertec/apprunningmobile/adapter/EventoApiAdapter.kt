package com.cibertec.apprunningmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.models.EventoApi

class EventoApiAdapter(
    private val lista: List<EventoApi>,
    private val clickListener: OnItemClickListener
): RecyclerView.Adapter<EventoApiAdapter.EventoApiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: EventoApi)
    }

    inner class EventoApiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val nombreEvento: TextView = itemView.findViewById(R.id.textViewNombreEventoApiLista)
        val cardView: CardView = itemView.findViewById(R.id.itemCardViewEventosApiLista)

        fun bind(eventoApi: EventoApi) {
            nombreEvento.text = eventoApi.nombreEvento

            cardView.setOnClickListener {
                clickListener.onItemClick(eventoApi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoApiViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_api_evento_lista, parent, false)
        return EventoApiViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoApiViewHolder, position: Int) {

        val eventoApi = lista[position]
        holder.nombreEvento.text = eventoApi.nombreEvento
        holder.bind(eventoApi)
    }

    override fun getItemCount(): Int = lista.size
}
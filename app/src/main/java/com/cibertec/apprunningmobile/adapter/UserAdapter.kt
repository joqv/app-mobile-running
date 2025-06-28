package com.cibertec.apprunningmobile.adapter // Asegúrate que el paquete sea correcto y crea la carpeta 'adapter'

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cibertec.apprunningmobile.R // Importa R para acceder a tus recursos de layout
import com.cibertec.apprunningmobile.models.Usuario // Importa tu clase Usuario

/**
 * Adaptador personalizado para mostrar una lista de objetos Usuario en un ListView.
 * Utiliza el layout item_usuario.xml para la visualización de cada ítem.
 */
class UserAdapter(private val context: Context, private var userList: List<Usuario>) : BaseAdapter() {

    /**
     * Actualiza la lista de datos del adaptador y notifica al ListView para que se redibuje.
     * @param newList La nueva lista de usuarios para mostrar.
     */
    fun updateData(newList: List<Usuario>) {
        userList = newList
        notifyDataSetChanged() // Este método es crucial para que el ListView se refresque
    }

    /**
     * Retorna el número total de ítems en la lista.
     */
    override fun getCount(): Int {
        return userList.size
    }

    /**
     * Retorna el objeto en la posición especificada de la lista.
     */
    override fun getItem(position: Int): Any {
        return userList[position]
    }

    /**
     * Retorna el ID de fila del ítem en la posición especificada.
     * En este caso, simplemente usamos la posición como ID.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Este método se llama para obtener una View que representa un ítem en la posición especificada.
     * Reutiliza vistas existentes (convertView) para un rendimiento eficiente.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        // Si la vista no es nula, significa que podemos reutilizarla
        if (convertView == null) {
            // Infla el layout personalizado del ítem (item_usuario.xml)
            view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false)
            // Crea un nuevo ViewHolder para guardar las referencias a las vistas
            holder = ViewHolder(view)
            // Asocia el ViewHolder a la vista usando setTag()
            view.tag = holder
        } else {
            // Si la vista ya existe, la reutilizamos y recuperamos el ViewHolder
            view = convertView
            holder = view.tag as ViewHolder
        }

        // Obtiene el objeto Usuario para la posición actual
        val usuario = userList[position]

        // Asigna los datos del usuario a las TextViews del layout del ítem
        holder.tvNombreCompleto.text = "${usuario.nombre} ${usuario.apellidos}"
        holder.tvEmail.text = usuario.email
        holder.tvSexoFechaNacimiento.text = "Sexo: ${usuario.sexo} - Fecha Nac: ${usuario.fechaNacimiento}"

        return view
    }

    /**
     * Clase interna ViewHolder para almacenar referencias a las vistas del ítem.
     * Esto evita llamadas repetidas a findViewById(), mejorando el rendimiento del ListView.
     */
    private class ViewHolder(view: View) {
        val tvNombreCompleto: TextView = view.findViewById(R.id.tvNombreCompleto)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val tvSexoFechaNacimiento: TextView = view.findViewById(R.id.tvSexoFechaNacimiento)
    }
}
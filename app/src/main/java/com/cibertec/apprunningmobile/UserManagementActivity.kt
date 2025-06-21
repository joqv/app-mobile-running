package com.cibertec.apprunningmobile // Asegúrate de que tu paquete sea correcto

import android.os.Bundle

import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprunningmobile.database.UsuarioDBHelper
import com.cibertec.apprunningmobile.models.Usuario
import com.cibertec.apprunningmobile.adapter.UserAdapter

class UserManagementActivity : AppCompatActivity() {

    private lateinit var dbHelper: UsuarioDBHelper
    private lateinit var lvUsuarios: ListView
    private lateinit var adapter: UserAdapter // Cambia a tu UserAdapter
    private var listaUsuarios = mutableListOf<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_management)

        dbHelper = UsuarioDBHelper(this)
        lvUsuarios = findViewById(R.id.lvUsuarios)

        // Inicializa el adaptador vacío o con la lista inicial
        adapter = UserAdapter(this, listaUsuarios) // Pasa 'this' como Context
        lvUsuarios.adapter = adapter

        cargarListaUsuarios() // Carga la lista de usuarios al iniciar

        lvUsuarios.setOnItemClickListener { _, _, position, _ ->
            val usuarioSeleccionado = listaUsuarios[position]
            mostrarDialogoEditarEliminarUsuario(usuarioSeleccionado)
        }
    }

    override fun onResume() {
        super.onResume()
        cargarListaUsuarios()
    }

    private fun cargarListaUsuarios() {
        listaUsuarios = dbHelper.obtenerTodosLosUsuarios().toMutableList()
        if (listaUsuarios.isEmpty()) {
            Toast.makeText(this, "No hay usuarios registrados.", Toast.LENGTH_LONG).show()
        }
        // Actualiza los datos en tu nuevo adaptador
        adapter.updateData(listaUsuarios)
        // Ya no se usa ArrayAdapter, así que esta línea se elimina o comenta:
        // val usuariosParaMostrar = listaUsuarios.map { "${it.nombre} ${it.apellidos} (${it.email}) - Sexo: ${it.sexo}" }
        // adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usuariosParaMostrar)
        // lvUsuarios.adapter = adapter // Esta línea ya no es necesaria aquí si el adaptador se inicializa una vez
    }


    private fun mostrarDialogoEditarEliminarUsuario(usuario: Usuario) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Acciones para ${usuario.nombre} ${usuario.apellidos}")
        builder.setMessage("Email: ${usuario.email}\nFecha Nac: ${usuario.fechaNacimiento}")

        builder.setPositiveButton("Editar") { dialog, which ->
            // --- LÓGICA PARA EDITAR USUARIO ---
            val intent = android.content.Intent(this, UserEditProfileActivity::class.java)
            intent.putExtra("usuarioId", usuario.id) // Pasa el ID del usuario a la actividad de edición
            startActivity(intent)
        }

        builder.setNegativeButton("Eliminar") { dialog, which ->
            // ... (el código de eliminar permanece igual)
            AlertDialog.Builder(this)
                .setTitle("Confirmar Eliminación")
                .setMessage("¿Estás seguro de que quieres eliminar a ${usuario.nombre} ${usuario.apellidos}?")
                .setPositiveButton("Sí, eliminar") { confirmDialog, _ ->
                    val filasEliminadas = dbHelper.eliminarUsuario(usuario.id)
                    if (filasEliminadas > 0) {
                        Toast.makeText(this, "Usuario eliminado correctamente.", Toast.LENGTH_SHORT).show()
                        cargarListaUsuarios() // Recarga la lista después de eliminar
                    } else {
                        Toast.makeText(this, "Error al eliminar usuario. No se encontró el ID o hubo un problema.", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        builder.setNeutralButton("Cancelar", null)
        builder.show()
    }
}
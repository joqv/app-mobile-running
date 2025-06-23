package com.cibertec.apprunningmobile // Ajusta este paquete

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprunningmobile.database.UsuarioDBHelper
import com.cibertec.apprunningmobile.models.Usuario
import java.util.Calendar

class UserEditProfileActivity : AppCompatActivity() {

    private lateinit var dbHelper: UsuarioDBHelper
    private var usuarioId: Int = -1 // Para almacenar el ID del usuario que estamos editando
    private var usuarioActual: Usuario? = null // Para mantener una referencia al usuario original

    // Declarar los EditTexts
    private lateinit var etNombre: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etSexo: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etFechaNacimiento: EditText
    private lateinit var btnGuardarCambios: Button
    private lateinit var btnCancelarEdicion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit_profile)

        dbHelper = UsuarioDBHelper(this)

        // Vinculación de vistas
        etNombre = findViewById(R.id.etEditNombre)
        etApellidos = findViewById(R.id.etEditApellidos)
        etSexo = findViewById(R.id.etEditSexo)
        etEmail = findViewById(R.id.etEditEmail)
        etPassword = findViewById(R.id.etEditPassword)
        etFechaNacimiento = findViewById(R.id.etEditFechaNacimiento)
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios)
        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicion)

        // Obtener el ID del usuario del Intent
        usuarioId = intent.getIntExtra("usuarioId", -1) // Obtiene el ID, -1 si no se encuentra
        if (usuarioId == -1) {
            Toast.makeText(this, "Error: No se proporcionó ID de usuario para editar.", Toast.LENGTH_LONG).show()
            finish() // Cierra la actividad si no hay ID válido
            return
        }

        // Cargar los datos del usuario para mostrarlos en los campos
        cargarDatosUsuario()

        // Configurar DatePickerDialog para la fecha de nacimiento
        etFechaNacimiento.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val formattedMonth = String.format("%02d", selectedMonth + 1)
                val formattedDay = String.format("%02d", selectedDay)
                etFechaNacimiento.setText("$formattedDay/$formattedMonth/$selectedYear")
            }, year, month, day)
            dpd.show()
        }

        // Listener para el botón Guardar Cambios
        btnGuardarCambios.setOnClickListener {
            guardarCambiosUsuario()
        }

        // Listener para el botón Cancelar
        btnCancelarEdicion.setOnClickListener {
            finish() // Simplemente cierra la actividad
        }
    }

    // Método para cargar los datos del usuario en los campos de edición
    private fun cargarDatosUsuario() {
        // Necesitamos un método en UsuarioDBHelper que obtenga un usuario por ID.
        // Lo agregaremos en el Paso 4. Por ahora, asumimos que existe.
        usuarioActual = dbHelper.obtenerUsuarioPorId(usuarioId)

        if (usuarioActual != null) {
            etNombre.setText(usuarioActual!!.nombre)
            etApellidos.setText(usuarioActual!!.apellidos)
            etSexo.setText(usuarioActual!!.sexo)
            etEmail.setText(usuarioActual!!.email)
            etPassword.setText(usuarioActual!!.password) // Aquí cargamos la contraseña (recuerda la advertencia de seguridad)
            etFechaNacimiento.setText(usuarioActual!!.fechaNacimiento)
        } else {
            Toast.makeText(this, "Usuario no encontrado.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    // Método para guardar los cambios del usuario
    private fun guardarCambiosUsuario() {
        val nuevoNombre = etNombre.text.toString().trim()
        val nuevoApellidos = etApellidos.text.toString().trim()
        val nuevoSexo = etSexo.text.toString().trim()
        val nuevoEmail = etEmail.text.toString().trim()
        val nuevaPassword = etPassword.text.toString().trim() // Recuperando la contraseña para guardar
        val nuevaFechaNacimiento = etFechaNacimiento.text.toString().trim()

        // Validaciones (similares a RegisterActivity)
        if (nuevoNombre.isEmpty() || nuevoApellidos.isEmpty() || nuevoSexo.isEmpty() ||
            nuevoEmail.isEmpty() || nuevaPassword.isEmpty() || nuevaFechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_LONG).show()
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(nuevoEmail).matches()) {
            Toast.makeText(this, "Ingresa un email válido.", Toast.LENGTH_LONG).show()
            return
        }

        // Crear una nueva instancia de Usuario con los datos modificados
        // y el ID del usuario original
        val usuarioModificado = Usuario(
            id = usuarioId, // ¡Importante! Mantenemos el ID original
            nombre = nuevoNombre,
            apellidos = nuevoApellidos,
            sexo = nuevoSexo,
            email = nuevoEmail,
            password = nuevaPassword,
            fechaNacimiento = nuevaFechaNacimiento
        )

        // Llamar al método de tu DBHelper para actualizar el usuario
        val filasAfectadas = dbHelper.actualizarUsuario(usuarioModificado)

        if (filasAfectadas > 0) {
            Toast.makeText(this, "Perfil actualizado correctamente.", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad de edición
        } else {
            Toast.makeText(this, "Error al actualizar el perfil.", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.cibertec.apprunningmobile // Ajusta este paquete a la ruta de tu proyecto

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprunningmobile.database.UsuarioDBHelper
import com.cibertec.apprunningmobile.models.Usuario
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: UsuarioDBHelper
    private lateinit var etNombre: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etSexo: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etFechaNacimiento: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnGoToLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // Asegúrate que este sea el layout correcto para tu registro

        dbHelper = UsuarioDBHelper(this) // Inicializa tu DBHelper

        // Vincula los elementos de la UI
        etNombre = findViewById(R.id.etRegisterNombre)
        etApellidos = findViewById(R.id.etRegisterApellidos)
        etSexo = findViewById(R.id.etRegisterSexo)
        etEmail = findViewById(R.id.etRegisterEmail)
        etPassword = findViewById(R.id.etRegisterPassword)
        etFechaNacimiento = findViewById(R.id.etRegisterFechaNacimiento)
        btnRegister = findViewById(R.id.btnRegister)
        btnGoToLogin = findViewById(R.id.btnGoToLogin)

        // Configurar DatePickerDialog para el campo de Fecha de Nacimiento
        etFechaNacimiento.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Formatear la fecha como DD/MM/AAAA (puedes ajustar el formato)
                val formattedMonth = String.format("%02d", selectedMonth + 1) // +1 porque el mes es 0-index
                val formattedDay = String.format("%02d", selectedDay)
                etFechaNacimiento.setText("$formattedDay/$formattedMonth/$selectedYear")
            }, year, month, day)
            dpd.show()
        }

        // Listener para el botón de Registrar
        btnRegister.setOnClickListener {
            // Obtener los valores de los EditTexts, eliminando espacios en blanco al inicio/final
            val nombre = etNombre.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val sexo = etSexo.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val fechaNacimiento = etFechaNacimiento.text.toString().trim()

            // Validaciones básicas de campos vacíos y formato de email
            if (nombre.isEmpty() || apellidos.isEmpty() || sexo.isEmpty() ||
                email.isEmpty() || password.isEmpty() || fechaNacimiento.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_LONG).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Por favor, ingresa un formato de email válido.", Toast.LENGTH_LONG).show()
            } else {
                // Antes de registrar, verificar si el email ya existe en la DB
                if (dbHelper.obtenerUsuarioPorEmail(email) != null) {
                    Toast.makeText(this, "El email ya está registrado. Por favor, usa otro.", Toast.LENGTH_LONG).show()
                } else {
                    // Crear un nuevo objeto Usuario con los datos
                    val nuevoUsuario = Usuario(
                        nombre = nombre,
                        apellidos = apellidos,
                        sexo = sexo,
                        email = email,
                        password = password, // Guardando contraseña en texto plano (recordar advertencia de seguridad)
                        fechaNacimiento = fechaNacimiento
                    )
                    // Insertar el usuario en la base de datos
                    val idInsertado = dbHelper.insertarUsuario(nuevoUsuario)

                    if (idInsertado > 0) { // Si idInsertado es > 0, la inserción fue exitosa
                        Toast.makeText(this, "¡Registro exitoso! Ya puedes iniciar sesión.", Toast.LENGTH_LONG).show()
                        finish() // Cierra la actividad de registro para volver a la de login
                    } else {
                        Toast.makeText(this, "Error al registrar usuario. Inténtalo de nuevo.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        // Listener para el botón de ir a Login (si ya tiene cuenta)
        btnGoToLogin.setOnClickListener {
            finish() // Cierra esta actividad para volver a la de login (si se navegó desde allí)
        }
    }
}
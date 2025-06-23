package com.cibertec.apprunningmobile.ui // Ajusta este paquete a la ruta de tu proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprunningmobile.R
import com.cibertec.apprunningmobile.database.UsuarioDBHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: UsuarioDBHelper
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoToRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Asegúrate que este sea el layout correcto para tu login

        dbHelper = UsuarioDBHelper(this) // Inicializa tu DBHelper

        // Vincula los elementos de la UI
        etEmail = findViewById(R.id.etLoginEmail)
        etPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoToRegister = findViewById(R.id.btnGoToRegister)

        // Listener para el botón de Login
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa email y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                // Llama al método de tu DBHelper para validar credenciales
                val usuarioAutenticado = dbHelper.validarCredenciales(email, password)
                if (usuarioAutenticado != null) {
                    Toast.makeText(this, "¡Bienvenido, ${usuarioAutenticado.nombre}!", Toast.LENGTH_SHORT).show()
                    // Si el login es exitoso, navega a la actividad principal
                    val intent = Intent(this, UserManagementActivity::class.java) // <--- Asegúrate que MainActivityPrincipal sea el nombre de tu actividad principal
                    startActivity(intent)
                    finish() // Finaliza LoginActivity para que el usuario no pueda volver con el botón atrás
                } else {
                    Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Listener para el botón de ir a Registro
        btnGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java) // <--- Asegúrate que RegisterActivity sea el nombre de tu actividad de registro
            startActivity(intent)
        }
    }
}
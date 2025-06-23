package com.cibertec.apprunningmobile.models

data class Usuario(
    var id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val sexo: String,
    val email: String,
    val password: String,
    val fechaNacimiento: String
)
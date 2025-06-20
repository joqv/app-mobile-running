package com.cibertec.apprunningmobile.models

data class AtletaApi(
    val idAtleta: Int,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val edad: Int,
    val sexo: String,
    val pais: PaisApi
)
package com.cibertec.apprunningmobile.models

import com.google.gson.annotations.SerializedName

data class AtletaApi(
    @SerializedName("idCorredor")
    val idAtleta: Int,
    val nombre: String?,
    val apellido: String?,
    val telefono: String?,
    val edad: Int?,
    val sexo: String?,
    val pais: PaisApi?
)
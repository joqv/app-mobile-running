package com.cibertec.apprunningmobile.models

import com.google.gson.annotations.SerializedName

data class ResultadoApi(
    var idResultado: Int,
    @SerializedName("corredor")
    var atleta: AtletaApi?,
    var evento: EventoApi?,
    var categoria: CategoriaApi?,
    var tiempo: String?,
    var puesto: Int?,
    var dorsal: String?
)
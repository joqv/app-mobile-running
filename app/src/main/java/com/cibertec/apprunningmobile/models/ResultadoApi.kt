package com.cibertec.apprunningmobile.models

data class ResultadoApi(
    var idResultado: Int,
    var atleta: AtletaApi,
    var evento: EventoApi,
    var categoria: CategoriaApi,
    var tiempo: String,
    var puesto: Int,
    var dorsal: String
)
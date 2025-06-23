package com.cibertec.apprunningmobile.network

import com.cibertec.apprunningmobile.models.ResultadoApi
import retrofit2.Call
import retrofit2.http.GET

interface ResultadoApiService {

    @GET("resultados")
    fun getResultadosApi(): Call<List<ResultadoApi>>
}
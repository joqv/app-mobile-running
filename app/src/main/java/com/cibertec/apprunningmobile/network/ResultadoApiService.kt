package com.cibertec.apprunningmobile.network

import com.cibertec.apprunningmobile.models.EventoApi
import com.cibertec.apprunningmobile.models.ResultadoApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ResultadoApiService {

    @GET("resultados")
    fun getResultadosApi(): Call<List<ResultadoApi>>

    @GET("resultados/eventos/{id}")
    fun getResultadosEventosApi(@Path("id") id: Int): Call<List<ResultadoApi>>

    @GET("eventos")
    fun getEventosApi(): Call<List<EventoApi>>
}
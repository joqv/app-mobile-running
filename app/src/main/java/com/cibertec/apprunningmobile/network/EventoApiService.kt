package com.cibertec.apprunningmobile.network

import com.cibertec.apprunningmobile.models.EventoApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventoApiService {

    @GET("eventos")
    fun getEventosApi(): Call<List<EventoApi>>

    @GET("eventos")
    fun getEventosApiPorNombre(@Query("n") nombre: String): Call<List<EventoApi>>
}
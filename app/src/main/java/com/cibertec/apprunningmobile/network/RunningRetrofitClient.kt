package com.cibertec.apprunningmobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RunningRetrofitClient {

    private const val BASE_URL = "https://runningapp.joseqv.com/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val resultadoApiService: ResultadoApiService by lazy {
        retrofit.create(ResultadoApiService::class.java)
    }

    val eventoApiService: EventoApiService by lazy {
        retrofit.create(EventoApiService::class.java)
    }
}
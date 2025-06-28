package com.cibertec.apprunningmobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RunningRetrofitClient {

    private const val BASE_URL = "https://runningapp.joseqv.com/api/"

    val instance: ResultadoApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ResultadoApiService::class.java)
    }
}
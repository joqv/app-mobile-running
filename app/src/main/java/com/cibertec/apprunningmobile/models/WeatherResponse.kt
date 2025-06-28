package com.cibertec.apprunningmobile.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("name") val name: String
)

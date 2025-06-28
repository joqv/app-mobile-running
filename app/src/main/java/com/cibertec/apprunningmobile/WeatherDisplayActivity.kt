package com.cibertec.apprunningmobile

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText // Importa los EditText
import android.widget.TextView
import android.widget.Toast // muestra un mensaje corto al usuario
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprunningmobile.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherDisplayActivity : AppCompatActivity() {

    private lateinit var tvWeatherResult: TextView
    private lateinit var etCityName: EditText // Declara el EditText
    private lateinit var btnSearchWeather: Button
    private lateinit var btnBack: Button

    private val API_KEY = "a8185da8871472845e120063b1709364" // clave de la api de openweathermap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_display)

        tvWeatherResult = findViewById(R.id.tvWeatherResult)
        etCityName = findViewById(R.id.etCityName) // Inicializa el EditText
        btnSearchWeather = findViewById(R.id.btnSearchWeather) // Inicializa el botón renombrado
        btnBack = findViewById(R.id.btnBack)

        //inicia la actividad y busca el clima de Lima por defecto
        fetchWeatherData("Lima") // Llama a la función con "Lima" como ciudad inicial

        //Configuracion del listener para la búsqueda
        btnSearchWeather.setOnClickListener {
            val city = etCityName.text.toString().trim() // Obtiene el texto del EditText y elimina espacios
            if (city.isNotEmpty()) {
                fetchWeatherData(city) // Llama a la función con la ciudad ingresada
            } else {
                Toast.makeText(this, "Por favor, ingresa el nombre de una ciudad.", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuracion del listener para el botón de volver
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun fetchWeatherData(city: String) { //la función recibe la ciudad como parámetro
        tvWeatherResult.text = "Buscando clima para $city..." // Actualiza el texto mientras busca

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getCurrentWeather(city, API_KEY) // Usa la ciudad del parámetro

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        weatherResponse?.let {
                            val weatherInfo = "Ciudad: ${it.name}\n" +
                                    "Temperatura: ${it.main.temp}°C\n" +
                                    "Humedad: ${it.main.humidity}%\n" +
                                    "Condición: ${it.weather[0].description}"
                            tvWeatherResult.text = weatherInfo
                            Log.d("WeatherDisplayActivity", "Clima obtenido: $weatherInfo")
                        } ?: run {
                            tvWeatherResult.text = "Error: Respuesta de clima vacía."
                            Log.e("WeatherDisplayActivity", "Respuesta de clima vacía.")
                            Toast.makeText(this@WeatherDisplayActivity, "No se encontraron datos para la ciudad.", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = when (response.code()) {
                            401 -> "Error 401: API Key inválida o inactiva."
                            404 -> "Error 404: Ciudad no encontrada. Intenta con otra."
                            else -> "Error API: ${response.code()}\n${errorBody}"
                        }
                        tvWeatherResult.text = errorMessage
                        Log.e("WeatherDisplayActivity", "Error en llamada API: ${response.code()} - $errorBody")
                        Toast.makeText(this@WeatherDisplayActivity, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    tvWeatherResult.text = "Error de conexión: ${e.message}"
                    Log.e("WeatherDisplayActivity", "Excepción al obtener clima: ${e.message}", e)
                    Toast.makeText(this@WeatherDisplayActivity, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
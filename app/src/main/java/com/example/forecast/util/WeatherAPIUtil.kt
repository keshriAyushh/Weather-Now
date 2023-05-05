package com.example.forecast.util

import android.content.Context
import android.widget.Toast
import com.example.forecast.API
import com.example.forecast.api.WeatherHelper
import com.example.forecast.api.WeatherService
import com.example.forecast.model.Weather
import kotlinx.coroutines.*
import retrofit2.create

object WeatherAPIUtil {

    private var weatherData: Weather? = null

    @OptIn(DelicateCoroutinesApi::class)
    public fun fetchWeatherData(city: String, context: Context) {

        val api = WeatherHelper.getInstance().create(WeatherService::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getWeatherData(city, API.API_KEY)
            if (response.isSuccessful) {

                if (response.body() != null) {
                    val responseBody = response.body()
                    withContext(Dispatchers.Main) {
                        weatherData = responseBody
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    public fun getWeatherData(): Weather? {
        return weatherData
    }
}
package com.example.forecast.api

import com.example.forecast.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("q")city: String,
        @Query("appid")appId: String
    ): Response<Weather>
}
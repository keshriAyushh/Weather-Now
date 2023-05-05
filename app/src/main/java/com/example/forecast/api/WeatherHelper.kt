package com.example.forecast.api

import com.example.forecast.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherHelper {

    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(API.BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}
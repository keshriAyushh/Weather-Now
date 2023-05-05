package com.example.forecast

object API {
    const val BASE_URL_WEATHER: String = "https://api.openweathermap.org/data/2.5/"
    const val dailyForecast: String = "api.openweathermap.org/data/2.5/forecast/daily?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}"
    const val geocodingApi: String = "http://api.openweathermap.org/geo/1.0/reverse?lat={lat}&lon={lon}&limit={limit}&appid={API key}"
    const val API_KEY: String = "8bb2232b75940b36f4e7d5f0fb60c41f"
}
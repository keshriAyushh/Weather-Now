package com.example.forecast

object API {
    const val BASE_URL_WEATHER: String = "https://api.openweathermap.org/data/2.5/"
    const val dailyForecast: String = "api.openweathermap.org/data/2.5/forecast/daily?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}"
    const val geocodingApi: String = "http://api.openweathermap.org/geo/1.0/reverse?lat={lat}&lon={lon}&limit={limit}&appid={API key}"
}

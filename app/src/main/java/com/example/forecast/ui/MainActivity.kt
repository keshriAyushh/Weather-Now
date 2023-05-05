package com.example.forecast.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.example.forecast.R
import com.example.forecast.databinding.ActivityMainBinding
import com.example.forecast.model.MyDate
import com.example.forecast.model.Weather
import com.example.forecast.util.DateUtil
import com.example.forecast.util.FormatText
import com.example.forecast.util.WeatherAPIUtil
import java.util.*
import kotlin.math.exp
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        updateUI(WeatherAPIUtil.getWeatherData()!!)
        binding.etSearchLayout.setEndIconOnClickListener{
            if(binding.etSearch.text.toString().isNotEmpty()) {
                WeatherAPIUtil.fetchWeatherData(binding.etSearch.text.toString(), this)
                updateUI(WeatherAPIUtil.getWeatherData()!!)
//                binding.etSearch.text?.clear()
            }
        }
    }

    private fun updateUI(weather: Weather) {
        binding.tvDate.text = DateUtil().getDate()
        if(weather.weather[0].main.contains("Clear")) {
            if(parseTime(weather.dt.toLong()) >= parseTime(weather.sys.sunrise.toLong()) &&
                    parseTime(weather.dt.toLong()) < parseTime(weather.sys.sunset.toLong())) {
                setLottie(R.raw.sunny)
            } else {
                setLottie(R.raw.moon)
            }
        } else if(weather.weather[0].main.contains("Rain")) {
            setLottie(R.raw.rain)
        } else if(weather.weather[0].main.contains("Clouds")) {
            setLottie(R.raw.cloudy)
        } else if(weather.weather[0].main.contains("Snow")) {
            setLottie(R.raw.sunny)
        }

        binding.tvWindSpeed.text = "${weather.wind.speed}km/h"
        binding.tvHumidity.text = "${weather.main.humidity}%"
        binding.tvFeltTemp.text = "${FormatText().roundOffDecimal(weather.main.feels_like - 273)}°C"
        binding.tvVisibility.text = "Visibility: ${weather.visibility/1000}km"
        if(weather.main.temp - 273 > 30) {
            binding.tvTemp.setTextColor(resources.getColor(R.color.temp_warm))
            binding.text2.setTextColor(resources.getColor(R.color.temp_warm))
            binding.tvTemp.text = "${(weather.main.temp - 273).roundToInt()}"
        } else {
            binding.tvTemp.setTextColor(resources.getColor(R.color.temp_cool))
            binding.text2.setTextColor(resources.getColor(R.color.temp_cool))
            binding.tvTemp.text = "${(weather.main.temp - 273).roundToInt()}"
        }
        binding.tvLocation.text = "${weather.name}, ${weather.sys.country}"
        binding.tvWeatherDescription.text = FormatText().formatString(weather.weather[0].description)
        val sunriseTime = parseTime(weather.sys.sunrise.toLong())
        val sunsetTime = parseTime(weather.sys.sunset.toLong())

        binding.tvSunTime.text = "Sunrise/Sunset: ${sunriseTime}AM/${sunsetTime}PM"

    }

    private fun parseTime(dtIn: Long): String {
        val expiry = Date(dtIn * 1000)
        val hour = if(expiry.hours > 12) expiry.hours - 12 else expiry.hours
        return "$hour:${expiry.minutes}"
    }

    private fun getTime(dtIn: Long): MyDate {
        val expiry = Date(dtIn * 1000)
        return MyDate(expiry.hours.toLong(), expiry.minutes.toLong())
    }

    private fun setLottie(resource: Int) {

        binding.ltAnimation.setAnimation(resource)
        binding.ltAnimation.loop(true)
        binding.ltAnimation.repeatMode = LottieDrawable.REVERSE
        binding.ltAnimation.playAnimation()
    }
}
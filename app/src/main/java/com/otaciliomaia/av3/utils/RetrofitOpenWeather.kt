package com.otaciliomaia.av3.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitOpenWeather {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val instance = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
        GsonConverterFactory.create())
        .build()
    fun getWeatherApi():WeatherApi {
        return instance.create(WeatherApi::class.java)
    }
}
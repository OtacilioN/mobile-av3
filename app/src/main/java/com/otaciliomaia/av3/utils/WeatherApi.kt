package com.otaciliomaia.av3.utils

import com.otaciliomaia.av3.data.model.CityWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("find")
    fun getWeatherData(
        @Query("q") city: String?,
        @Query("units") metric: String?,
        @Query("lang") language: String?,
        @Query("appid") apiKey: String?
    ): Call<CityWeather>
}
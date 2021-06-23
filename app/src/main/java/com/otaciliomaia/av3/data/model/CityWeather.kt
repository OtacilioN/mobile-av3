package com.otaciliomaia.av3.data.model

import com.google.gson.annotations.SerializedName

class CityWeather {
    @SerializedName("list")
    var resultList: ArrayList<CityWeather> = ArrayList<CityWeather>()
    class Main {
        @SerializedName("temp")
        var temp = 0f
    }
    class Weather {
        @SerializedName("id")
        var id = 0
    }
    @SerializedName("id")
    var id = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("main")
    var main: Main? = null
}
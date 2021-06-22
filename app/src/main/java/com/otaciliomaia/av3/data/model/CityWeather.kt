package com.otaciliomaia.av3.data.model

import com.google.gson.annotations.SerializedName

class CityWeather {
    class Main {
        @SerializedName("temp")
        var temp = 0f
        @SerializedName("feels_like")
        var feels_like = 0f
        @SerializedName("temp_min")
        var temp_min = 0f
        @SerializedName("temp_max")
        var temp_max = 0f
        @SerializedName("pressure")
        var pressure = 0f
        @SerializedName("humidity")
        var humidity = 0f
    }
    class Weather {
        @SerializedName("id")
        var id = 0
        @SerializedName("main")
        var main: String? = null
        @SerializedName("description")
        var description: String? = null
        @SerializedName("icon")
        var icon: String? = null
    }

    class Coord {
        @SerializedName("lon")
        var lon = 0f
        @SerializedName("lat")
        var lat = 0f
    }
    class Wind {
        @SerializedName("speed")
        var speed = 0f
        @SerializedName("deg")
        var deg = 0f
    }
    class Sys {
        @SerializedName("country")
        var country: String? = null
    }
    class Rain {
        @SerializedName("1h")
        var h1 = 0f
    }
    class Clouds {
        @SerializedName("all")
        var all = 0f
    }
    @SerializedName("id")
    var id = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("coord")
    var coord: Coord? = null
    @SerializedName("main")
    var main: Main? = null
    @SerializedName("dt")
    var dt = 0f
    @SerializedName("wind")
    var wind: Wind? = null
    @SerializedName("sys")
    var sys: Sys? = null
    @SerializedName("rain")
    var rain: Rain? = null
    @SerializedName("weather")
    var weather: ArrayList<Weather> = ArrayList<Weather>()
    @SerializedName("clouds")
    var clouds: Clouds? = null
    @SerializedName("cod")
    var cod = 0f
}
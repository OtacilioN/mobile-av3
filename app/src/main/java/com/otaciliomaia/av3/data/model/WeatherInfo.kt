package com.otaciliomaia.av3.data.model

import com.google.gson.annotations.SerializedName

data class WeatherInfo (
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String,
    @SerializedName("description")
    var descripton: String,
    @SerializedName("icon")
    var icon: String
)
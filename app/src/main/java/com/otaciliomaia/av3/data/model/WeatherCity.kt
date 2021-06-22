package com.otaciliomaia.av3.data.model

import com.google.gson.annotations.SerializedName

class WeatherCity {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("icon")
    var icon: String? = null
}
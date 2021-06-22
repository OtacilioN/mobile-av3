package com.otaciliomaia.av3.data.model

import com.google.gson.annotations.SerializedName

class WeatherMain {
    @SerializedName("temp")
    var temp=0f

    @SerializedName("feels_like")
    var feels_like=0f

    @SerializedName("temp_min")
    var temp_min=0f

    @SerializedName("temp_max")
    var temp_max=0f

    @SerializedName("pressure")
    var pressure=0f

    @SerializedName("humidity")
    var himidity=0f
}
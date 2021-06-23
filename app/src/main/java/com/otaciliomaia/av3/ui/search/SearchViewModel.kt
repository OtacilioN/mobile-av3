package com.otaciliomaia.av3.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otaciliomaia.av3.data.model.CityWeather
import com.otaciliomaia.av3.data.service.SharedPreferences

class SearchViewModel : ViewModel() {
    var weatherLiveData = MutableLiveData<ArrayList<CityWeather>>()
    var weatherList = ArrayList<CityWeather>()
    val sharedPreferences = SharedPreferences()

    fun updateList(resultList: ArrayList<CityWeather>) {
        weatherList.clear()
        weatherList = resultList
        weatherLiveData.postValue(weatherList)
    }

    fun observeWeatherList(): LiveData<ArrayList<CityWeather>>{
        return weatherLiveData
    }

    fun loadLang(context: Context?): String {
        var lang = "en"
        if(context != null){
            val prefs = sharedPreferences.getPreferences(context)

            if (prefs != null) {
                val isPT = prefs.getBoolean("isPT", false)
                if(isPT){
                    lang = "pt_br"
                }
            }
        }
        return lang
    }

    fun loadUnit(context: Context?): String {
        var unit = "metric"
        if(context != null){
            val prefs = sharedPreferences.getPreferences(context)

            if (prefs != null) {
                val isF = prefs.getBoolean("isF", false)
                if(isF){
                    unit = "imperial"
                }
            }
        }
        return unit
    }
}
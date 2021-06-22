package com.otaciliomaia.av3.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otaciliomaia.av3.data.model.CityWeather

class SearchViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
    var weatherLiveData = MutableLiveData<ArrayList<CityWeather>>()
    var weatherList = ArrayList<CityWeather>()

    fun updateList(resultList: ArrayList<CityWeather>){
        weatherList.clear()
        weatherList=resultList
        weatherLiveData.value=weatherList
    }

//    val text: LiveData<String> = _text
}
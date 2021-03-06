package com.otaciliomaia.av3.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otaciliomaia.av3.R
import com.otaciliomaia.av3.data.model.CityWeather

class WeatherAdapter(private val weatherList: ArrayList<CityWeather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherViewHolder {
        val weatherView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(weatherView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItem = weatherList[position]
        holder.cityName.text = weatherItem.name
        holder.temperature.text = weatherItem.main?.temp?.toString()
        //holder.weatherIcon.setImageResource(weatherItem.weather)
    }

    override fun getItemCount(): Int = weatherList.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.city_name)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val weatherIcon: ImageView = itemView.findViewById(R.id.weather_icon)
    }

    fun addData(list: ArrayList<CityWeather>) {
        weatherList.clear()
        weatherList.addAll(list)
    }
}
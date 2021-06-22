package com.otaciliomaia.av3.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaciliomaia.av3.R
import com.otaciliomaia.av3.data.model.CityWeather
import com.otaciliomaia.av3.data.model.WeatherItem
import com.otaciliomaia.av3.ui.settings.SettingsFragment
import com.otaciliomaia.av3.ui.settings.SettingsViewModel
import com.otaciliomaia.av3.utils.RetrofitOpenWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    val apiKey="167cdb68c8baa99813c299b8158044ba"
    private lateinit var searchViewModel: SearchViewModel
    lateinit var searchEntry:EditText
    private lateinit var recyclerView: RecyclerView
    lateinit var progressBar:ProgressBar
    lateinit var adapter:WeatherAdapter


    var weatherList:MutableList<WeatherItem> = ArrayList<WeatherItem>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        weatherList.add(WeatherItem("cidade",39.0,1))

        searchViewModel =
                ViewModelProvider(this)
                    .get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val context=requireActivity().applicationContext
        recyclerView=root.findViewById(R.id.weather_recycler_view) as RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter=WeatherAdapter(weatherList)
        recyclerView.adapter=adapter

        val searchBtn = root.findViewById<Button>(R.id.button)
        progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)
        searchEntry=root.findViewById(R.id.search_text)
        progressBar.visibility=View.INVISIBLE
        val cityNameSearch = searchEntry.text.toString()
        searchBtn.setOnClickListener {
            searchCity(cityNameSearch)
        }

//        searchViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        }
        return root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun searchCity(cityName: String){
        val context=requireActivity().applicationContext
        if (!isInternetAvailable(context)){
            Toast.makeText(context,R.string.no_connection, Toast.LENGTH_SHORT).show()
            return
        }
        progressBar.visibility=View.VISIBLE
        //getpreferences
        val preferences=SettingsViewModel()
        val prefUnit = preferences.observeUnit()
        // true = F (imperial) ; false = C (metric)
        val prefLang = preferences.observeLang()
        // true = pt_br ; false = en
        val retrofitApi=RetrofitOpenWeather().getWeatherApi()
        val searchResult= retrofitApi?.getWeatherData(cityName, "metric", "en", apiKey)

        searchResult?.enqueue(object : Callback<CityWeather>{
            override fun onResponse(call: Call<CityWeather>, response: Response<CityWeather>) {
                progressBar.visibility=View.INVISIBLE
                val resultCity=response.body()
                if (resultCity!=null){
                    searchViewModel.updateList(resultCity.resultList)
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<CityWeather>, t: Throwable) {
                progressBar.visibility=View.INVISIBLE
                Toast.makeText(getContext(),getString(R.string.no_result_found), Toast.LENGTH_SHORT).show()
            }
        })
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        } else {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) result = true
                else if(type == ConnectivityManager.TYPE_MOBILE) result = true
            }
        }
        return result
    }

}
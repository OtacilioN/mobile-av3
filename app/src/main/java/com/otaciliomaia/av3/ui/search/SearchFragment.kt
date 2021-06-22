package com.otaciliomaia.av3.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaciliomaia.av3.R
import com.otaciliomaia.av3.data.model.WeatherItem

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    lateinit var searchEntry:EditText
    lateinit var recyclerView: RecyclerView

    var weatherList:MutableList<WeatherItem> = ArrayList<WeatherItem>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //teste
        weatherList.add(WeatherItem("cidade",39.0,1))

        searchViewModel =
                ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val context=requireActivity().applicationContext
        recyclerView=root.findViewById(R.id.weather_recycler_view) as RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter=WeatherAdapter(weatherList)
        recyclerView.adapter=adapter
        searchViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun searchCity(){
        val context=requireActivity().applicationContext
        if (!isInternetAvailable(context)){
            Toast.makeText(context,R.string.no_connection, Toast.LENGTH_SHORT).show()
            return
        }

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
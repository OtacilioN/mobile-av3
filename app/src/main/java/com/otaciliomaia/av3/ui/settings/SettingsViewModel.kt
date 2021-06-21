package com.otaciliomaia.av3.ui.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otaciliomaia.av3.data.service.SharedPreferences

class SettingsViewModel : ViewModel() {
    val sharedPreferences = SharedPreferences()
    private val isF = MutableLiveData<Boolean>()
    private val isPT = MutableLiveData<Boolean>()

    fun observeUnit(): LiveData<Boolean>{
        return isF
    }
    fun observeLang(): LiveData<Boolean>{
        return isPT
    }

    fun loadPreferences(context: Context?){
        if(context != null){
            val prefs = sharedPreferences.getPreferences(context)
            if (prefs != null) {
                isF.postValue(prefs.getBoolean("isF", false))
                isPT.postValue(prefs.getBoolean("isPT", false))
            }
        }
    }

    fun setPreferences(context: Context?, isF: Boolean, isPT:Boolean){
        if(context != null){
            sharedPreferences.setPreferences(context, isF, isPT)
        }
    }
}
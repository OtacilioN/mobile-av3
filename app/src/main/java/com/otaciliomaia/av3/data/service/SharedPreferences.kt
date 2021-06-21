package com.otaciliomaia.av3.data.service

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences {
    fun getPreferences(context: Context): SharedPreferences? {
        return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }
    fun setPreferences(context: Context, isF: Boolean, isPT: Boolean){
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.apply{
            putBoolean("isF", isF)
            putBoolean("isPT", isPT)
            apply()
        }
    }
}
package com.otaciliomaia.av3.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.otaciliomaia.av3.R

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
                ViewModelProvider(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        settingsViewModel.loadPreferences(this.context)
        settingsViewModel.observeUnit().observe(viewLifecycleOwner, Observer {
            val unitRadioGroup: RadioGroup = root.findViewById(R.id.temperature_unit_group)
            if(it){
                unitRadioGroup.check(R.id.f_btn)
            }
            else{
                unitRadioGroup.check(R.id.celcius_btn)
            }
        })
        settingsViewModel.observeLang().observe(viewLifecycleOwner, Observer {
            val langRadioGroup: RadioGroup = root.findViewById(R.id.lang_radio_group)
            if(it){
                langRadioGroup.check(R.id.pt_btn)
            }
            else{
                langRadioGroup.check(R.id.en_btn)
            }
        })
        val saveBtn: Button = root.findViewById(R.id.save_settings_btn)
        saveBtn.setOnClickListener{
            val fBtn: RadioButton = root.findViewById(R.id.f_btn)
            val isPTBtn: RadioButton = root.findViewById(R.id.pt_btn)
            val isF = fBtn.isChecked
            val isPT = isPTBtn.isChecked
            settingsViewModel.setPreferences(this.context, isF, isPT)
        }
        return root
    }
}
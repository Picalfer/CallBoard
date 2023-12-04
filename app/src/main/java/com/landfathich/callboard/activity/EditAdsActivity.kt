package com.landfathich.callboard.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.landfathich.callboard.R
import com.landfathich.callboard.databinding.ActivityEditAdsBinding
import com.landfathich.callboard.dialoghelper.SpinnerDialogHelper
import com.landfathich.callboard.utils.CityHelper

class EditAdsActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditAdsBinding
    private val dialog = SpinnerDialogHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater).also { setContentView(it.root) }
        init()
    }

    private fun init() {

    }

    //OnClicks
    fun onClickSelectCountry(view: View) {
        val countryList = CityHelper.getAllCountries(this)
        dialog.showSpinnerDialog(this, countryList, binding.tvSelectCountry)
        if (binding.tvSelectCity.text.toString() != getString(R.string.select_city)) {
            binding.tvSelectCity.text = getString(R.string.select_city)
        }
    }

    fun onClickSelectCity(view: View) {
        val selectedCountry = binding.tvSelectCountry.text.toString()
        if (selectedCountry != getString(R.string.select_country)) {
            val cityList = CityHelper.getAllCities(this, selectedCountry)
            dialog.showSpinnerDialog(this, cityList, binding.tvSelectCity)
        } else {
            Toast.makeText(this, "No country selected", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.landfathich.callboard.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        dialog.showSpinnerDialog(this, countryList)
    }
}
package com.landfathich.callboard.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.landfathich.callboard.databinding.ActivityEditAdsBinding
import com.landfathich.callboard.dialoghelper.SpinnerDialogHelper
import com.landfathich.callboard.utils.CityHelper

class EditAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val countryList = CityHelper.getAllCountries(this)

        val dialog = SpinnerDialogHelper()
        dialog.showSpinnerDialog(this, countryList)
    }
}
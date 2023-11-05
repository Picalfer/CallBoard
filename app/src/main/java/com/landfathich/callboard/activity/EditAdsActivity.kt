package com.landfathich.callboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.landfathich.callboard.R
import com.landfathich.callboard.databinding.ActivityEditAdsBinding

class EditAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater).also { setContentView(it.root) }

    }
}
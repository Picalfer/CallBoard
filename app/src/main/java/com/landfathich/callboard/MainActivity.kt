package com.landfathich.callboard

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.landfathich.callboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        init()
    }

    private fun init() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.mainContent.toolbar,
            R.string.open,
            R.string.close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_ads -> {
                Toast.makeText(this, "Выбрано мои объявления", Toast.LENGTH_SHORT).show()
            }

            R.id.car -> {
                Toast.makeText(this, "Выбрано машины", Toast.LENGTH_SHORT).show()
            }

            R.id.pc -> {
                Toast.makeText(this, "Выбрано пк", Toast.LENGTH_SHORT).show()
            }

            R.id.smartphone -> {
                Toast.makeText(this, "Выбрано смартфоны", Toast.LENGTH_SHORT).show()
            }

            R.id.domestic -> {
                Toast.makeText(this, "Выбрано бытовая техника", Toast.LENGTH_SHORT).show()
            }

            R.id.sign_in -> {
                Toast.makeText(this, "Выбрано вход", Toast.LENGTH_SHORT).show()
            }

            R.id.sign_up -> {
                Toast.makeText(this, "Выбрано регистрация", Toast.LENGTH_SHORT).show()
            }

            R.id.sign_out -> {
                Toast.makeText(this, "Выбрано выход", Toast.LENGTH_SHORT).show()
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
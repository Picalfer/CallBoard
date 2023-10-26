package com.landfathich.callboard

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.landfathich.callboard.databinding.ActivityMainBinding
import com.landfathich.callboard.dialoghelper.DialogConst
import com.landfathich.callboard.dialoghelper.DialogHelper

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val myAuth = FirebaseAuth.getInstance()
    private lateinit var tvAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        init()
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(myAuth.currentUser)
    }

    private fun init() {
        tvAccount = binding.navView.getHeaderView(0).findViewById(R.id.tv_account_email)

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

            R.id.sign_in -> dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)

            R.id.sign_up -> dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)


            R.id.sign_out -> {
                myAuth.signOut()
                uiUpdate(null)
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if (user == null) getString(R.string.not_sign) else user.email
    }
}
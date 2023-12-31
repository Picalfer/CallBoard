package com.landfathich.callboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.landfathich.callboard.accounthepler.GoogleAccountConst
import com.landfathich.callboard.activity.EditAdsActivity
import com.landfathich.callboard.databinding.ActivityMainBinding
import com.landfathich.callboard.dialoghelper.SignDialogConst
import com.landfathich.callboard.dialoghelper.SignDialogHelper

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val dialogHelper = SignDialogHelper(this)
    val myAuth = FirebaseAuth.getInstance()
    private lateinit var tvAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GoogleAccountConst.GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    dialogHelper.accountHelper.signInFirebaseWithGoogle(account.idToken!!)
                }

            } catch (e: ApiException) {
                Log.d("TEST", "Api error : ${e.message}")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(myAuth.currentUser)
    }

    private fun init() {
        setSupportActionBar(binding.mainContent.toolbar)
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

            R.id.sign_in -> dialogHelper.createSignDialog(SignDialogConst.SIGN_IN_STATE)

            R.id.sign_up -> dialogHelper.createSignDialog(SignDialogConst.SIGN_UP_STATE)


            R.id.sign_out -> {
                myAuth.signOut()
                dialogHelper.accountHelper.signOutWithGoogle()
                uiUpdate(null)
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.new_ads) {
            val intent = Intent(this, EditAdsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if (user == null) getString(R.string.not_sign) else user.email
    }
}
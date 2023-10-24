package com.landfathich.callboard.dialoghelper

import android.app.AlertDialog
import com.landfathich.callboard.MainActivity
import com.landfathich.callboard.R
import com.landfathich.callboard.accounthepler.AccountHelper
import com.landfathich.callboard.databinding.SignDialogBinding

class DialogHelper(activity: MainActivity) {
    private val activity = activity
    private val accountHelper = AccountHelper(activity)
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(activity)
        val binding = SignDialogBinding.inflate(activity.layoutInflater)
        val view = binding.root

        if (index == DialogConst.SIGN_UP_STATE) {
            binding.tvSignTitle.text = activity.getString(R.string.aс_sign_up)
            binding.btnSignUpIn.text = activity.getString(R.string.sign_up_action)
        } else {
            binding.tvSignTitle.text = activity.getString(R.string.aс_sign_in)
            binding.btnSignUpIn.text = activity.getString(R.string.sign_in_action)
        }

        binding.btnSignUpIn.setOnClickListener {
            if (index == DialogConst.SIGN_UP_STATE) {
                accountHelper.signUpWithEmail(
                    binding.etSignEmail.text.toString(),
                    binding.etSignPassword.text.toString()
                )
            } else {

            }
        }

        builder.setView(view)
        builder.show()
    }
}
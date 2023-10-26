package com.landfathich.callboard.dialoghelper

import android.app.AlertDialog
import com.landfathich.callboard.MainActivity
import com.landfathich.callboard.R
import com.landfathich.callboard.accounthepler.AccountHelper
import com.landfathich.callboard.databinding.SignDialogBinding

class DialogHelper(private val activity: MainActivity) {
    private val accountHelper = AccountHelper(activity)
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(activity)
        val binding = SignDialogBinding.inflate(activity.layoutInflater)
        val view = binding.root
        builder.setView(view)

        if (index == DialogConst.SIGN_UP_STATE) {
            binding.tvSignTitle.text = activity.getString(R.string.aс_sign_up)
            binding.btnSignUpIn.text = activity.getString(R.string.sign_up_action)
        } else {
            binding.tvSignTitle.text = activity.getString(R.string.aс_sign_in)
            binding.btnSignUpIn.text = activity.getString(R.string.sign_in_action)
        }

        val dialog = builder.create()
        binding.btnSignUpIn.setOnClickListener {
            dialog.dismiss()
            if (index == DialogConst.SIGN_UP_STATE) {
                accountHelper.signUpWithEmail(
                    binding.etSignEmail.text.toString(),
                    binding.etSignPassword.text.toString()
                )
            } else {
                accountHelper.signInWithEmail(
                    binding.etSignEmail.text.toString(),
                    binding.etSignPassword.text.toString()
                )
            }
        }

        dialog.show()
    }
}
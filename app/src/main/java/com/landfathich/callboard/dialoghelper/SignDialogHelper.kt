package com.landfathich.callboard.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.landfathich.callboard.MainActivity
import com.landfathich.callboard.R
import com.landfathich.callboard.accounthepler.AccountHelper
import com.landfathich.callboard.databinding.SignDialogBinding

class SignDialogHelper(private val activity: MainActivity) {
    val accountHelper = AccountHelper(activity)
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(activity)
        val binding = SignDialogBinding.inflate(activity.layoutInflater)
        val view = binding.root
        builder.setView(view)

        setDialogState(index, binding)

        val dialog = builder.create()
        binding.btnSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, binding, dialog)
        }
        binding.btnForgetPassword.setOnClickListener {
            setOnClickForgetPassword(binding, dialog)
        }
        binding.btnGoogleSignIn.setOnClickListener {
            accountHelper.signInWithGoogle()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setOnClickForgetPassword(binding: SignDialogBinding, dialog: AlertDialog?) {
        if (binding.etSignEmail.text.isNotEmpty()) {
            activity.myAuth.sendPasswordResetEmail(binding.etSignEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            activity,
                            activity.getString(R.string.email_reset_password_was_sent),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            activity,
                            activity.getString(R.string.reset_password_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            dialog?.dismiss()
        } else {
            binding.tvDialogMessage.visibility = View.VISIBLE
        }
    }

    private fun setDialogState(index: Int, binding: SignDialogBinding) {
        if (index == SignDialogConst.SIGN_UP_STATE) {
            binding.tvSignTitle.text = activity.getString(R.string.aс_sign_up)
            binding.btnSignUpIn.text = activity.getString(R.string.sign_up_action)
        } else {
            binding.tvSignTitle.text = activity.getString(R.string.aс_sign_in)
            binding.btnSignUpIn.text = activity.getString(R.string.sign_in_action)
            binding.btnForgetPassword.visibility = View.VISIBLE
        }
    }

    private fun setOnClickSignUpIn(index: Int, binding: SignDialogBinding, dialog: AlertDialog?) {
        dialog?.dismiss()
        if (index == SignDialogConst.SIGN_UP_STATE) {
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
}
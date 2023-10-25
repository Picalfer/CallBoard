package com.landfathich.callboard.accounthepler

import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.landfathich.callboard.MainActivity
import com.landfathich.callboard.R

class AccountHelper(activity: MainActivity) {
    private val activity = activity
    fun signUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() and password.isNotEmpty()) {
            activity.myAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sendEmailVerification(task.result.user!!)
                        activity.uiUpdate(task.result.user)
                    } else {
                        Toast.makeText(
                            activity,
                            activity.getString(R.string.sign_up_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.send_verification_email_done),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.send_verification_email_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
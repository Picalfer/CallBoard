package com.landfathich.callboard.accounthepler

import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.landfathich.callboard.MainActivity
import com.landfathich.callboard.R
import com.landfathich.callboard.constants.FirebaseAuthConstants

class AccountHelper(private val activity: MainActivity) {
    private lateinit var googleSignInClient: GoogleSignInClient
    fun signUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() and password.isNotEmpty()) {
            activity.myAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sendEmailVerification(task.result.user!!)
                        activity.uiUpdate(task.result.user)
                    } else {
                        Log.d("TEST", "Exception: ${task.exception}")
                        if (task.exception is FirebaseAuthUserCollisionException) {
                            val exception = task.exception as FirebaseAuthUserCollisionException
                            if (exception.errorCode == FirebaseAuthConstants.ERROR_EMAIL_ALREADY_IN_USE) {
                                bindEmailToGoogleAccount(email, password)
                            }
                        }

                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            val exception =
                                task.exception as FirebaseAuthInvalidCredentialsException
                            if (exception.errorCode == FirebaseAuthConstants.ERROR_INVALID_EMAIL) {
                                Toast.makeText(activity, exception.errorCode, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        if (task.exception is FirebaseAuthWeakPasswordException) {
                            val exception = task.exception as FirebaseAuthWeakPasswordException
                            if (exception.errorCode == FirebaseAuthConstants.ERROR_WEAK_PASSWORD) {
                                Toast.makeText(activity, exception.errorCode, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
        }
    }

    fun signInWithEmail(email: String, password: String) {
        if (email.isNotEmpty() and password.isNotEmpty()) {
            activity.myAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        activity.uiUpdate(task.result.user)
                    } else {
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            val exception =
                                task.exception as FirebaseAuthInvalidCredentialsException
                            if (exception.errorCode == FirebaseAuthConstants.ERROR_INVALID_EMAIL) {
                                Toast.makeText(activity, exception.errorCode, Toast.LENGTH_SHORT)
                                    .show()
                            } else if (exception.errorCode == FirebaseAuthConstants.ERROR_WRONG_PASSWORD) {
                                Toast.makeText(activity, exception.errorCode, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else if (task.exception is FirebaseAuthInvalidUserException) {
                            val exception =
                                task.exception as FirebaseAuthInvalidUserException
                            if (exception.errorCode == FirebaseAuthConstants.ERROR_USER_NOT_FOUND) {
                                Toast.makeText(activity, exception.errorCode, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
        }
    }

    private fun getGoogleSignInClient(): GoogleSignInClient {
        val googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("785976875343-imic14rfp41vaj2v2l7896okca5mq1s0.apps.googleusercontent.com")
                .requestEmail()
                .build()
        return GoogleSignIn.getClient(activity, googleSignInOptions)
    }

    fun signInWithGoogle() {
        googleSignInClient = getGoogleSignInClient()
        val intent = googleSignInClient.signInIntent
        activity.startActivityForResult(intent, GoogleAccountConst.GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    fun signOutWithGoogle() {
        googleSignInClient = getGoogleSignInClient()
        googleSignInClient.signOut()
    }

    fun signInFirebaseWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        activity.myAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(activity, "Sign in done", Toast.LENGTH_SHORT).show()
                activity.uiUpdate(task.result?.user)
            } else {
                Log.d("TEST", "Google exception: ${task.exception}")
            }
        }
    }

    private fun bindEmailToGoogleAccount(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        if (activity.myAuth.currentUser != null) {
            activity.myAuth.currentUser?.linkWithCredential(credential)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            activity,
                            activity.getString(R.string.bind_done),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(activity, activity.getString(R.string.enter_google), Toast.LENGTH_SHORT)
                .show()
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
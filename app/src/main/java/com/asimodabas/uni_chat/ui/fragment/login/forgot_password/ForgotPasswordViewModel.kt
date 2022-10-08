package com.asimodabas.uni_chat.ui.fragment.login.forgot_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordViewModel : ViewModel() {

    private val auth = Firebase.auth
    val animation = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun forgotPassword(email: String) {
        forgotPasswordFirebase(email)
    }

    private fun forgotPasswordFirebase(email: String) {
        animation.value = true
        auth.sendPasswordResetEmail(email).addOnCompleteListener { process ->
            if (process.isSuccessful) {
                success.value = true
                animation.value = false
            }
        }.addOnFailureListener { error ->
            animation.value = false
            errorMessage.value = error.localizedMessage
        }
    }
}
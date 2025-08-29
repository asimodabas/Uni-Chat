package com.asimodabas.uni_chat.ui.fragment.auth.forgot_password

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordViewModel : ViewModel() {

    private val auth = Firebase.auth
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success
    
    private val _validationError = MutableLiveData<String>()
    val validationError: LiveData<String> = _validationError

    fun processForgotPassword(email: String) {
        val trimmedEmail = email.trim()
        
        if (trimmedEmail.isEmpty()) {
            _validationError.value = "Please enter your email address"
            return
        }
        
        if (!Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            _validationError.value = "Please enter a valid email address"
            return
        }
        
        // Email is valid, proceed with password reset
        forgotPasswordFirebase(trimmedEmail)
    }

    fun forgotPassword(email: String) {
        forgotPasswordFirebase(email)
    }

    private fun forgotPasswordFirebase(email: String) {
        _isLoading.value = true
        auth.sendPasswordResetEmail(email).addOnCompleteListener { process ->
            if (process.isSuccessful) {
                _success.value = true
                _isLoading.value = false
            }
        }.addOnFailureListener { error ->
            _isLoading.value = false
            _errorMessage.value = error.localizedMessage
        }
    }
}
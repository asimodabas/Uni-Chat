package com.asimodabas.uni_chat.ui.fragment.auth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    private val _fieldErrors = MutableLiveData<Map<String, String>>()
    val fieldErrors: LiveData<Map<String, String>> = _fieldErrors

    private val _shouldClearFieldErrors = MutableLiveData<Boolean>()
    val shouldClearFieldErrors: LiveData<Boolean> = _shouldClearFieldErrors

    private val _validationError = MutableLiveData<String>()
    val validationError: LiveData<String> = _validationError

    fun checkCurrentUser() {
        val currentUser = auth.currentUser
        _isUserLoggedIn.value = currentUser != null
    }

    fun processLogin(email: String, password: String) {
        _shouldClearFieldErrors.value = true
        val errors = validateFields(email, password)
        if (errors.isNotEmpty()) {
            _fieldErrors.value = errors
            _validationError.value = "Please enter all information completely"
        } else {
            signIn(email, password)
        }
    }

    fun signIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Please enter both email and password"
            return
        }

        _isLoading.value = true
        
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _isLoading.value = false
                _loginSuccess.value = true
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _error.value = exception.localizedMessage ?: "Login failed"
                _loginSuccess.value = false
            }
    }

    fun validateFields(email: String, password: String): Map<String, String> {
        val errors = mutableMapOf<String, String>()
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()

        if (trimmedEmail.isEmpty()) {
            errors["email"] = "Please enter valid email address"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            errors["email"] = "Please enter a valid email address"
        }

        if (trimmedPassword.isEmpty()) {
            errors["password"] = "Please enter valid password address"
        } else if (trimmedPassword.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        return errors
    }
}

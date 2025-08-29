package com.asimodabas.uni_chat.ui.fragment.auth.create

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asimodabas.uni_chat.Constants.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore: FirebaseFirestore = Firebase.firestore

    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> = _registrationSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _clearFields = MutableLiveData<Boolean>()
    val clearFields: LiveData<Boolean> = _clearFields

    private val _fieldErrors = MutableLiveData<Map<String, String>>()
    val fieldErrors: LiveData<Map<String, String>> = _fieldErrors

    private val _shouldClearFieldErrors = MutableLiveData<Boolean>()
    val shouldClearFieldErrors: LiveData<Boolean> = _shouldClearFieldErrors

    private val _validationError = MutableLiveData<String>()
    val validationError: LiveData<String> = _validationError

    fun processUserRegistration(email: String, name: String, surname: String, password: String) {
        // Clear previous field errors
        _shouldClearFieldErrors.value = true
        
        // Validate fields
        val errors = validateFields(email, name, surname, password)
        if (errors.isNotEmpty()) {
            _fieldErrors.value = errors
            _validationError.value = "Please enter all information completely"
        } else {
            // Fields are valid, proceed with user creation
            createUser(email, name, surname, password)
        }
    }

    fun createUser(email: String, name: String, surname: String, password: String) {
        if (!validateInput(email, name, surname, password)) {
            _error.value = "Please enter all information completely"
            return
        }

        _isLoading.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // User created successfully, now save user data to Firestore
                saveUserDataToFirestore(name, surname, email)
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _error.value = exception.localizedMessage ?: "Registration failed"
            }
    }

    private fun saveUserDataToFirestore(name: String, surname: String, email: String) {
        val dataMap = HashMap<String, Any>()
        dataMap["name"] = name
        dataMap["surname"] = surname
        dataMap["email"] = email

        firestore.collection(USERS).add(dataMap)
            .addOnSuccessListener {
                _isLoading.value = false
                _clearFields.value = true
                _registrationSuccess.value = true
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _clearFields.value = true
                _error.value = exception.localizedMessage ?: "Failed to save user data"
            }
    }

    private fun validateInput(email: String, name: String, surname: String, password: String): Boolean {
        return email.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && password.isNotEmpty()
    }

    fun validateFields(email: String, name: String, surname: String, password: String): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (email.isEmpty()) {
            errors["email"] = "Email is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors["email"] = "Please enter a valid email address"
        }

        if (name.isEmpty()) {
            errors["name"] = "Name is required"
        }

        if (surname.isEmpty()) {
            errors["surname"] = "Surname is required"
        }

        if (password.isEmpty()) {
            errors["password"] = "Password is required"
        } else if (password.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        return errors
    }
}

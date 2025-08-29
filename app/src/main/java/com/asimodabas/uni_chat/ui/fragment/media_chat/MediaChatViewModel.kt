package com.asimodabas.uni_chat.ui.fragment.media_chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asimodabas.uni_chat.Constants
import com.asimodabas.uni_chat.model.UniMedia
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MediaChatViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore: FirebaseFirestore = Firebase.firestore

    private val _medias = MutableLiveData<List<UniMedia>>()
    val medias: LiveData<List<UniMedia>> = _medias

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _logoutSuccess = MutableLiveData<Boolean>()
    val logoutSuccess: LiveData<Boolean> = _logoutSuccess

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    fun getMediaMessages(departmentId: Int) {
        val collectionPath = getMediaCollectionPath(departmentId)

        _isLoading.value = true

        firestore.collection(collectionPath).orderBy(Constants.DATE, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                _isLoading.value = false

                if (error != null) {
                    _error.value = error.localizedMessage ?: "Error occurred"
                } else {
                    if (value != null) {
                        if (value.isEmpty) {
                            _isEmpty.value = true
                            _medias.value = emptyList()
                        } else {
                            _isEmpty.value = false

                            val mediaList = mutableListOf<UniMedia>()
                            val documents = value.documents

                            for (document in documents) {
                                val userEmail = document.get("userEmail") as String
                                val downloadUrl = document.get("downloadUrl") as String
                                val uniMedia = UniMedia(userEmail, downloadUrl)
                                mediaList.add(uniMedia)
                            }

                            _medias.value = mediaList
                        }
                    }
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _logoutSuccess.value = true
    }

    private fun getMediaCollectionPath(departmentId: Int): String {
        return when (departmentId) {
            // Engineer
            1 -> "Engineer-Computer-Media"
            2 -> "Engineer-Chemical-Media"
            3 -> "Engineer-Industry-Media"
            4 -> "Engineer-Build-Media"
            5 -> "Engineer-Food-Media"
            6 -> "Engineer-Electric-Media"
            7 -> "Engineer-Machine-Media"
            // Teacher
            8 -> "Teacher-Physics-Media"
            9 -> "Teacher-Literature-Media"
            10 -> "Teacher-Chemical-Media"
            11 -> "Teacher-Maths-Media"
            12 -> "Teacher-Biology-Media"
            13 -> "Teacher-English-Media"
            14 -> "Teacher-Geography-Media"
            15 -> "Teacher-History-Media"
            // Health
            16 -> "Health-Medicine-Media"
            17 -> "Health-Dentist-Media"
            18 -> "Health-Nurse-Media"
            19 -> "Health-Psychology-Media"
            20 -> "Health-Pharmacy-Media"
            21 -> "Health-Veterinary-Media"
            22 -> "Health-Dietetics-Media"
            23 -> "Health-Rehabilitation-Media"
            // Language
            24 -> "Language-English-Media"
            25 -> "Language-Deutsch-Media"
            26 -> "Language-French-Media"
            27 -> "Language-Russian-Media"
            28 -> "Language-Japanese-Media"
            else -> "Public-Media"
        }
    }
}
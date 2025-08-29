package com.asimodabas.uni_chat.ui.fragment.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asimodabas.uni_chat.Constants
import com.asimodabas.uni_chat.model.UniChat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore: FirebaseFirestore = Firebase.firestore

    private val _chats = MutableLiveData<List<UniChat>>()
    val chats: LiveData<List<UniChat>> = _chats

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _messageSent = MutableLiveData<Boolean>()
    val messageSent: LiveData<Boolean> = _messageSent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _logoutSuccess = MutableLiveData<Boolean>()
    val logoutSuccess: LiveData<Boolean> = _logoutSuccess

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _validationError = MutableLiveData<String>()
    val validationError: LiveData<String> = _validationError

    fun processSendMessage(chatType: Int, messageText: String) {
        val trimmedMessage = messageText.trim()
        
        if (trimmedMessage.isEmpty()) {
            _validationError.value = "Please enter a message"
            return
        }
        
        if (trimmedMessage.length > 500) {
            _validationError.value = "Message is too long (max 500 characters)"
            return
        }
        
        // Message is valid, proceed with sending
        sendMessage(chatType, trimmedMessage)
    }

    fun getChatMessages(chatType: Int) {
        val collectionPath = getChatCollectionPath(chatType)

        _isLoading.value = true

        firestore.collection(collectionPath).orderBy(Constants.DATE, Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->
                _isLoading.value = false

                if (error != null) {
                    _error.value = error.localizedMessage
                } else {
                    if (value != null) {
                        if (value.isEmpty) {
                            _isEmpty.value = true
                            _chats.value = emptyList()
                        } else {
                            _isEmpty.value = false

                            val chatList = mutableListOf<UniChat>()
                            val documents = value.documents

                            for (document in documents) {
                                val text = document.get(Constants.TEXT) as String
                                val user = document.get(Constants.USER) as String
                                val chat = UniChat(user, text)
                                chatList.add(chat)
                            }

                            _chats.value = chatList
                        }
                    }
                }
            }
    }

    fun sendMessage(chatType: Int, messageText: String) {
        auth.currentUser?.let { user ->
            val email = user.email ?: return
            val date = FieldValue.serverTimestamp()

            val dataMap = HashMap<String, Any>()
            dataMap["text"] = messageText
            dataMap["user"] = email
            dataMap["date"] = date

            val collectionPath = getChatCollectionPath(chatType)

            firestore.collection(collectionPath).add(dataMap)
                .addOnSuccessListener {
                    _messageSent.value = true
                }
                .addOnFailureListener {
                    _error.value = it.localizedMessage
                    _messageSent.value = false
                }
        }
    }

    fun signOut() {
        auth.signOut()
        _logoutSuccess.value = true
    }

    private fun getChatCollectionPath(chatType: Int): String {
        return when (chatType) {
            0 -> "Public-Chat"
            // Engineer
            1 -> "Engineer-Computer-Chat"
            2 -> "Engineer-Chemical-Chat"
            3 -> "Engineer-Industry-Chat"
            4 -> "Engineer-Build-Chat"
            5 -> "Engineer-Food-Chat"
            6 -> "Engineer-Electric-Chat"
            7 -> "Engineer-Machine-Chat"
            // Teacher
            8 -> "Teacher-Physics-Chat"
            9 -> "Teacher-Literature-Chat"
            10 -> "Teacher-Chemical-Chat"
            11 -> "Teacher-Maths-Chat"
            12 -> "Teacher-Biology-Chat"
            13 -> "Teacher-English-Chat"
            14 -> "Teacher-Geography-Chat"
            15 -> "Teacher-History-Chat"
            // Health
            16 -> "Health-Medicine-Chat"
            17 -> "Health-Dentist-Chat"
            18 -> "Health-Nurse-Chat"
            19 -> "Health-Psychology-Chat"
            20 -> "Health-Pharmacy-Chat"
            21 -> "Health-Veterinary-Chat"
            22 -> "Health-Dietetics-Chat"
            23 -> "Health-Rehabilitation-Chat"
            // Language
            24 -> "Language-English-Chat"
            25 -> "Language-Deutsch-Chat"
            26 -> "Language-French-Chat"
            27 -> "Language-Russian-Chat"
            28 -> "Language-Japanese-Chat"
            else -> "Public-Chat"
        }
    }
}
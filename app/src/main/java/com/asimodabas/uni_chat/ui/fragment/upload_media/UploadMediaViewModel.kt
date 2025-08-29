package com.asimodabas.uni_chat.ui.fragment.upload_media

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asimodabas.uni_chat.Constants
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class UploadMediaViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore: FirebaseFirestore = Firebase.firestore
    private val storage: FirebaseStorage = Firebase.storage

    private val _uploadSuccess = MutableLiveData<Boolean>()
    val uploadSuccess: LiveData<Boolean> = _uploadSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedImage = MutableLiveData<Uri?>()
    val selectedImage: LiveData<Uri?> = _selectedImage

    private val _permissionNeeded = MutableLiveData<Boolean>()
    val permissionNeeded: LiveData<Boolean> = _permissionNeeded

    fun setSelectedImage(uri: Uri?) {
        _selectedImage.value = uri
    }

    fun uploadMedia(departmentId: Int) {
        val selectedImageUri = _selectedImage.value
        if (selectedImageUri == null) {
            _error.value = "Please select an image first"
            return
        }

        val collectionPath = getChatCollectionPath(departmentId)
        uploadImageToStorage(selectedImageUri, collectionPath)
    }

    private fun uploadImageToStorage(imageUri: Uri, collectionPath: String) {
        _isLoading.value = true

        val uuid = UUID.randomUUID()
        val imageName = "$uuid.jpg"

        val reference = storage.reference
        val imageReference = reference.child(Constants.IMAGES).child(imageName)

        imageReference.putFile(imageUri)
            .addOnSuccessListener {
                // Get download URL
                val uploadPictureReference =
                    storage.reference.child(Constants.IMAGES).child(imageName)
                uploadPictureReference.downloadUrl
                    .addOnSuccessListener { downloadUri ->
                        saveMediaToFirestore(downloadUri.toString(), collectionPath)
                    }
                    .addOnFailureListener { exception ->
                        _isLoading.value = false
                        _error.value = exception.localizedMessage ?: "Failed to get download URL"
                    }
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _error.value = exception.localizedMessage ?: "Failed to upload image"
            }
    }

    private fun saveMediaToFirestore(downloadUrl: String, collectionPath: String) {
        auth.currentUser?.let { user ->
            val mediaMap = hashMapOf<String, Any>()
            mediaMap["downloadUrl"] = downloadUrl
            mediaMap["date"] = Timestamp.now()
            mediaMap["userEmail"] = user.email!!

            firestore.collection(collectionPath).add(mediaMap)
                .addOnSuccessListener {
                    _isLoading.value = false
                    _uploadSuccess.value = true
                }
                .addOnFailureListener { exception ->
                    _isLoading.value = false
                    _error.value = exception.localizedMessage ?: "Failed to save media data"
                }
        } ?: run {
            _isLoading.value = false
            _error.value = "User not authenticated"
        }
    }

    fun getChatCollectionPath(departmentId: Int): String {
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

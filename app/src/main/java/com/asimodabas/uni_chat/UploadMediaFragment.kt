package com.asimodabas.uni_chat

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asimodabas.uni_chat.databinding.FragmentUploadMediaBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.*

class UploadMediaFragment : Fragment() {

    private var _binding: FragmentUploadMediaBinding? = null
    private val binding get() = _binding!!
    var selectedImage: Uri? = null
    var selectedBitmap: Bitmap? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private val args: UploadMediaFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        firestore = Firebase.firestore
        storage = Firebase.storage

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUploadMediaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uploadImageView.setOnClickListener {
            selected_image(it)

        }

        binding.UploadButton.setOnClickListener {
            when(args.departmantId) {
                1 -> {
                    upload("Computer-Media")

                }
                2 -> {
                    upload("Chemical-Media")

                }
                3 -> {
                    upload("Industry-Media")

                }
                4 -> {
                    upload("Build-Media")

                }
                5 -> {
                    upload("Food-Media")

                }
                6 -> {
                    upload("Electric-Media")

                }
                7 -> {
                    upload("Machine-Media")

                }

            }
        }

    }

    fun selected_image(view: View) {
        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    it.applicationContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                val Intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(Intent, 2)
            }
        }
    }

    private fun upload(path: String) {

        //random uuid
        val uuid = UUID.randomUUID()
        val imageName = "$uuid.jpg"

        val reference = storage.reference
        val imageReference = reference.child("images").child(imageName)

        if (selectedImage != null) {
            imageReference.putFile(selectedImage!!).addOnSuccessListener {
                //download url

                val uploadPictureReferance = storage.reference.child("images").child(imageName)
                uploadPictureReferance.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()

                    if (auth.currentUser != null) {

                        val mediaMap = hashMapOf<String, Any>()

                        mediaMap.put("downloadUrl", downloadUrl)
                        mediaMap.put("date", Timestamp.now())
                        mediaMap.put("userEmail", auth.currentUser!!.email!!)

                        firestore.collection(path).add(mediaMap).addOnSuccessListener {

                            Toast.makeText(requireContext(), "Paylaşım Başarılı", Toast.LENGTH_LONG)
                                .show()

                            findNavController().navigateUp()

                        }.addOnFailureListener {
                            Toast.makeText(requireContext(), "Hata", Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val Intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(Intent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage = data.data
        }
        try {

            context?.let {
                if (selectedImage != null) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(it.contentResolver, selectedImage!!)
                        selectedBitmap = ImageDecoder.decodeBitmap(source)
                        binding.uploadImageView.setImageBitmap(selectedBitmap)

                    } else {
                        selectedBitmap =
                            MediaStore.Images.Media.getBitmap(it.contentResolver, selectedImage)
                        binding.uploadImageView.setImageBitmap(selectedBitmap)
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
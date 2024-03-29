package com.asimodabas.uni_chat.ui.fragment.common

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.uni_chat.Constants.DATE
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.adapter.MediaRecyclerAdapter
import com.asimodabas.uni_chat.databinding.FragmentMediaChatBinding
import com.asimodabas.uni_chat.model.UniMedia
import com.asimodabas.uni_chat.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MediaChatFragment : Fragment(R.layout.fragment_media_chat) {

    private val binding by viewBinding(FragmentMediaChatBinding::bind)
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var mediaArrayList: ArrayList<UniMedia>
    private lateinit var mediaAdapter: MediaRecyclerAdapter
    private val args: MediaChatFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        auth = Firebase.auth
        firestore = Firebase.firestore
        storage = Firebase.storage
        mediaArrayList = ArrayList<UniMedia>()

        binding.mediaChatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mediaAdapter = MediaRecyclerAdapter()
        binding.mediaChatRecyclerView.adapter = mediaAdapter

        when (args.departmentId) {
//Engineer
            1 -> {
                getData("Engineer-Computer-Media")
            }
            2 -> {
                getData("Engineer-Chemical-Media")
            }
            3 -> {
                getData("Engineer-Industry-Media")
            }
            4 -> {
                getData("Engineer-Build-Media")
            }
            5 -> {
                getData("Engineer-Food-Media")
            }
            6 -> {
                getData("Engineer-Electric-Media")
            }
            7 -> {
                getData("Engineer-Machine-Media")
            }
//Teacher
            8 -> {
                getData("Teacher-Physics-Media")
            }
            9 -> {
                getData("Teacher-Literature-Media")
            }
            10 -> {
                getData("Teacher-Chemical-Media")
            }
            11 -> {
                getData("Teacher-Maths-Media")
            }
            12 -> {
                getData("Teacher-Biology-Media")
            }
            13 -> {
                getData("Teacher-English-Media")
            }
            14 -> {
                getData("Teacher-Geography-Media")
            }
            15 -> {
                getData("Teacher-History-Media")
            }
//Health
            16 -> {
                getData("Health-Medicine-Media")
            }
            17 -> {
                getData("Health-Dentist-Media")
            }
            18 -> {
                getData("Health-Nurse-Media")
            }
            19 -> {
                getData("Health-Psychology-Media")
            }
            20 -> {
                getData("Health-Pharmacy-Media")
            }
            21 -> {
                getData("Health-Veterinary-Media")
            }
            22 -> {
                getData("Health-Dietetics-Media")
            }
            23 -> {
                getData("Health-Rehabilitation-Media")
            }
//Language
            24 -> {
                getData("Language-English-Media")
            }
            25 -> {
                getData("Language-Deutsch-Media")
            }
            26 -> {
                getData("Language-French-Media")
            }
            27 -> {
                getData("Language-Russian-Media")
            }
            28 -> {
                getData("Language-Japanese-Media")
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action =
                MediaChatFragmentDirections.actionMediaChatFragmentToUploadMediaFragment(
                    args.departmentId
                )
            findNavController().navigate(action)
        }
    }

    private fun getData(collectionPath: String) {
        firestore.collection(collectionPath).orderBy(DATE, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(requireContext(), "Hata", Toast.LENGTH_LONG).show()
                } else {
                    if (value != null) {
                        if (value.isEmpty) {
                            Toast.makeText(
                                requireContext(),
                                R.string.no_message,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val documents = value.documents

                            mediaArrayList.clear()

                            for (document in documents) {

                                val useremail = document.get("userEmail") as String
                                val downloadUrl = document.get("downloadUrl") as String

                                val uniMedia = UniMedia(useremail, downloadUrl)
                                mediaArrayList.add(uniMedia)
                            }
                            mediaAdapter.medias = mediaArrayList
                            mediaAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lagout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.signout) {
            auth.signOut()

            val action =
                MediaChatFragmentDirections.actionMediaChatFragmentToLoginFragment()
            findNavController().navigate(action)

        }
        return super.onOptionsItemSelected(item)
    }
}
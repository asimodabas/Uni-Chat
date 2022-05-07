package com.asimodabas.uni_chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.uni_chat.databinding.FragmentMediaChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MediaChatFragment : Fragment() {

    private var _binding: FragmentMediaChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var mediaArrayList: ArrayList<UniMedia>
    private lateinit var mediaAdapter: MediaRecyclerAdapter
    private val args: MediaChatFragmentArgs by navArgs()

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
        _binding = FragmentMediaChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaArrayList = ArrayList<UniMedia>()

        binding.mediaChatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mediaAdapter = MediaRecyclerAdapter()
        binding.mediaChatRecyclerView.adapter = mediaAdapter

        when (args.departmentId) {

            1 -> {
                getData("Computer-Media")
            }
            2 -> {
                getData("Chemical-Media")
            }
            3 -> {
                getData("Industry-Media")
            }
            4 -> {
                getData("Build-Media")
            }
            5 -> {
                getData("Food-Media")
            }
            6 -> {
                getData("Electric-Media")
            }
            7 -> {
                getData("Machine-Media")
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action =
                MediaChatFragmentDirections.actionMediaChatFragmentToUploadMediaFragment(args.departmentId)
            findNavController().navigate(action)
        }
    }

    private fun getData(collectionPath: String) {

        firestore.collection(collectionPath).orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(requireContext(), "Hata", Toast.LENGTH_LONG).show()
                } else {
                    if (value != null) {
                        if (!value.isEmpty) {
                            val documents = value.documents

                            mediaArrayList.clear()

                            for (document in documents) {


                                val useremail = document.get("userEmail") as String
                                val downloadUrl = document.get("downloadUrl") as String

                                println(downloadUrl)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.asimodabas.uni_chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.uni_chat.databinding.FragmentEngineerBinding
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
    private lateinit var mediaArrayList : ArrayList<UniMedia>
    private lateinit var mediaAdapter : MediaRecyclerAdapter


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

        getData()

        binding.mediaChatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mediaAdapter = MediaRecyclerAdapter(mediaArrayList)
        binding.mediaChatRecyclerView.adapter = mediaAdapter

        binding.floatingActionButton.setOnClickListener {
            val action = MediaChatFragmentDirections.actionMediaChatFragmentToUpdateMediaFragment()
            findNavController().navigate(action)
        }

    }

    fun getData(){

        firestore.collection("Computer-Media").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(requireContext(),"Hata",Toast.LENGTH_LONG).show()
            }else{
                if (value!=null){
                    if (!value.isEmpty){
                        val documents = value.documents

                        mediaArrayList.clear()

                        for (document in documents){

                            val useremail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            println(downloadUrl)

                            val uniMedia = UniMedia(useremail,downloadUrl)
                            mediaArrayList.add(uniMedia)
                        }

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
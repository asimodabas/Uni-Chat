package com.asimodabas.uni_chat

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.uni_chat.databinding.FragmentChatBinding
import com.asimodabas.uni_chat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter : ChatRecyclerAdapter
    private var chats = arrayListOf<UniChat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        auth = Firebase.auth
        firestore = Firebase.firestore

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= ChatRecyclerAdapter()
        binding.chatRecyclerView.adapter = adapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.sendButton.setOnClickListener {

            auth.currentUser?.let {
                val user = it.email
                val chatText = binding.chatText.text.toString()
                val date = FieldValue.serverTimestamp()

                val dataMap = HashMap<String, Any>()

                dataMap.put("text", chatText)
                dataMap.put("user", user!!)
                dataMap.put("date", date)

                firestore.collection("Public-Chat").add(dataMap).addOnSuccessListener {
                    binding.chatText.setText("")
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                    binding.chatText.setText("")
                }
            }
        }

        firestore.collection("Public-Chat").orderBy("date", Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (value != null) {
                        if (value.isEmpty) {
                            Toast.makeText(requireContext(), "Mesaj Yok", Toast.LENGTH_SHORT).show()
                        } else {

                            // Data pulled from firebase database

                            val documents = value.documents

                            chats.clear() //dump existing messages before loop

                            for (document in documents) {
                                val text = document.get("text") as String
                                val user = document.get("user") as String

                                // println(text)

                                val chat = UniChat(user, text)

                                chats.add(chat)
                                //ChatFragment chats == ChatRecyclerAdapter chats
                                adapter.chats = chats

                            }
                        }

                        adapter.notifyDataSetChanged()

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

            val action = ChatFragmentDirections.actionChatFragmentToLoginFragment()
            findNavController().navigate(action)

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
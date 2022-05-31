package com.asimodabas.uni_chat.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.adapter.ChatRecyclerAdapter
import com.asimodabas.uni_chat.databinding.FragmentChatBinding
import com.asimodabas.uni_chat.model.UniChat
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
    private lateinit var adapter: ChatRecyclerAdapter
    private var chats = arrayListOf<UniChat>()
    private val args: ChatFragmentArgs by navArgs()

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

        adapter = ChatRecyclerAdapter()
        binding.chatRecyclerView.adapter = adapter

        when (args.chatType) {
            0 -> {
                getChatMessages("Public-Chat")
            }
//Engineer
            1 -> {
                getChatMessages("Engineer-Computer-Chat")
            }
            2 -> {
                getChatMessages("Engineer-Chemical-Chat")
            }
            3 -> {
                getChatMessages("Engineer-Industry-Chat")
            }
            4 -> {
                getChatMessages("Engineer-Build-Chat")
            }
            5 -> {
                getChatMessages("Engineer-Food-Chat")
            }
            6 -> {
                getChatMessages("Engineer-Electric-Chat")
            }
            7 -> {
                getChatMessages("Engineer-Machine-Chat")
            }
//Teacher
            8 -> {
                getChatMessages("Teacher-Physics-Chat")
            }
            9 -> {
                getChatMessages("Teacher-Literature-Chat")
            }
            10 -> {
                getChatMessages("Teacher-Chemical-Chat")
            }
            11 -> {
                getChatMessages("Teacher-Maths-Chat")
            }
            12 -> {
                getChatMessages("Teacher-Biology-Chat")
            }
            13 -> {
                getChatMessages("Teacher-English-Chat")
            }
            14 -> {
                getChatMessages("Teacher-Geography-Chat")
            }
            15 -> {
                getChatMessages("Teacher-History-Chat")
            }
//Health
            16 -> {
                getChatMessages("Health-Medicine-Chat")
            }
            17 -> {
                getChatMessages("Health-Dentist-Chat")
            }
            18 -> {
                getChatMessages("Health-Nurse-Chat")
            }
            19 -> {
                getChatMessages("Health-Psychology-Chat")
            }
            20 -> {
                getChatMessages("Health-Pharmacy-Chat")
            }
            21 -> {
                getChatMessages("Health-Veterinary-Chat")
            }
            22 -> {
                getChatMessages("Health-Dietetics-Chat")
            }
            23 -> {
                getChatMessages("Health-Rehabilitation-Chat")
            }
//Language
            24 -> {
                getChatMessages("Language-English-Chat")
            }
            25 -> {
                getChatMessages("Language-Deutsch-Chat")
            }
            26 -> {
                getChatMessages("Language-French-Chat")
            }
            27 -> {
                getChatMessages("Language-Russian-Chat")
            }
            28 -> {
                getChatMessages("Language-Japanese-Chat")
            }
        }

        binding.sendButton.setOnClickListener {

            auth.currentUser?.let {
                val user = it.email
                val chatText = binding.chatText.text.toString()
                val date = FieldValue.serverTimestamp()

                val dataMap = HashMap<String, Any>()

                dataMap.put("text", chatText)
                dataMap.put("user", user!!)
                dataMap.put("date", date)

                when (args.chatType) {
                    0 -> {
                        sendMessage("Public-Chat", dataMap)
                    }
//Engineer
                    1 -> {
                        sendMessage("Engineer-Computer-Chat", dataMap)
                    }
                    2 -> {
                        sendMessage("Engineer-Chemical-Chat", dataMap)
                    }
                    3 -> {
                        sendMessage("Engineer-Industry-Chat", dataMap)
                    }
                    4 -> {
                        sendMessage("Engineer-Build-Chat", dataMap)
                    }
                    5 -> {
                        sendMessage("Engineer-Food-Chat", dataMap)
                    }
                    6 -> {
                        sendMessage("Engineer-Electric-Chat", dataMap)
                    }
                    7 -> {
                        sendMessage("Engineer-Machine-Chat", dataMap)
                    }
//Teacher
                    8 -> {
                        sendMessage("Teacher-Physics-Chat", dataMap)
                    }
                    9 -> {
                        sendMessage("Teacher-Literature-Chat", dataMap)
                    }
                    10 -> {
                        sendMessage("Teacher-Chemical-Chat", dataMap)
                    }
                    11 -> {
                        sendMessage("Teacher-Maths-Chat", dataMap)
                    }
                    12 -> {
                        sendMessage("Teacher-Biology-Chat", dataMap)
                    }
                    13 -> {
                        sendMessage("Teacher-English-Chat", dataMap)
                    }
                    14 -> {
                        sendMessage("Teacher-Geography-Chat", dataMap)
                    }
                    15 -> {
                        sendMessage("Teacher-History-Chat", dataMap)
                    }
//Health
                    16 -> {
                        sendMessage("Health-Medicine-Chat", dataMap)
                    }
                    17 -> {
                        sendMessage("Health-Dentist-Chat", dataMap)
                    }
                    18 -> {
                        sendMessage("Health-Nurse-Chat", dataMap)
                    }
                    19 -> {
                        sendMessage("Health-Psychology-Chat", dataMap)
                    }
                    20 -> {
                        sendMessage("Health-Pharmacy-Chat", dataMap)
                    }
                    21 -> {
                        sendMessage("Health-Veterinary-Chat", dataMap)
                    }
                    22 -> {
                        sendMessage("Health-Dietetics-Chat", dataMap)
                    }
                    23 -> {
                        sendMessage("Health-Rehabilitation-Chat", dataMap)
                    }
//Language
                    24 -> {
                        sendMessage("Language-English-Chat", dataMap)
                    }
                    25 -> {
                        sendMessage("Language-Deutsch-Chat", dataMap)
                    }
                    26 -> {
                        sendMessage("Language-French-Chat", dataMap)
                    }
                    27 -> {
                        sendMessage("Language-Russian-Chat", dataMap)
                    }
                    28 -> {
                        sendMessage("Language-Japanese-Chat", dataMap)
                    }
                }
            }
        }
    }

    private fun sendMessage(path: String, dataMap: HashMap<String, Any>) {
        firestore.collection(path).add(dataMap).addOnSuccessListener {
            binding.chatText.setText("")
        }.addOnFailureListener {
            Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            binding.chatText.setText("")
        }
    }

    private fun getChatMessages(collectionPath: String) {
        firestore.collection(collectionPath).orderBy("date", Query.Direction.ASCENDING)
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

            val action =
                MediaChatFragmentDirections.actionMediaChatFragmentToLoginFragment()
            findNavController().navigate(action)

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
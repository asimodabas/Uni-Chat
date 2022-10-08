package com.asimodabas.uni_chat.ui.fragment.login.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentCreateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateFragment : Fragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firestore = Firebase.firestore
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()

            if (email.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && pass.isNotEmpty()) {

                auth.createUserWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnSuccessListener {

                    val dataMap = HashMap<String, Any>()

                    dataMap.put("name", name)
                    dataMap.put("surname", surname)
                    dataMap.put("email", email)

                    firestore.collection("Users").add(dataMap).addOnSuccessListener {

                        binding.nameEditText.setText("")
                        binding.surnameEditText.setText("")

                        val action =
                            CreateFragmentDirections.actionCreateFragmentToSecondFragment()
                        findNavController().navigate(action)

                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG)
                            .show()
                        binding.nameEditText.setText("")
                        binding.surnameEditText.setText("")
                    }

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.enter_your_information_completely,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.asimodabas.uni_chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.databinding.FragmentCreateBinding
import com.asimodabas.uni_chat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateFragment : Fragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val email2 = binding.emailEditText.text.toString()
        val name = binding.nameEditText.text.toString()
        val surname = binding.surnameEditText.text.toString()
        val pass = binding.passwordEditText.text.toString()


        binding.createButton.setOnClickListener {
            
            auth.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ).addOnSuccessListener {
                val action = CreateFragmentDirections.actionCreateFragmentToChatFragment()
                findNavController().navigate(action)

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
package com.asimodabas.uni_chat.ui.fragment.login.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser != null) {
            val action =
                LoginFragmentDirections.actionLoginFragmentToSecondFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {

            val email = binding.emailText.text.toString().trim()
            val password = binding.passwordText.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                binding.emailText.error = "Lütfen geçerli bir mail adresi giriniz."
            }
            if (TextUtils.isEmpty(password)) {
                binding.passwordText.error = "Lütfen geçerli bir şifre giriniz."
            }

            if (email.equals("") || password.equals("")) {
                Toast.makeText(
                    context,
                    R.string.enter_your_information_completely,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.signInWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.passwordText.text.toString()
                ).addOnSuccessListener {

                    val action =
                        LoginFragmentDirections.actionLoginFragmentToSecondFragment()
                    findNavController().navigate(action)

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.textViewForgetPassword.setOnClickListener {
            val action =
                LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            findNavController().navigate(action)
        }

        binding.signupButton.setOnClickListener {

            val action =
                LoginFragmentDirections.actionLoginFragmentToCreateFragment()
            findNavController().navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
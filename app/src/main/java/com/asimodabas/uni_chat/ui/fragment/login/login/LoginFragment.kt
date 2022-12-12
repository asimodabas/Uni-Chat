package com.asimodabas.uni_chat.ui.fragment.login.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentLoginBinding
import com.asimodabas.uni_chat.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        val currentUser = Firebase.auth.currentUser

        if (currentUser != null) {
            val action =
                LoginFragmentDirections.actionLoginFragmentToSecondFragment()
            findNavController().navigate(action)
        }

        binding.loginButton.setOnClickListener {

            val email = binding.emailText.text.toString().trim()
            val password = binding.passwordText.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                binding.emailText.error = getString(R.string.Please_enter_valid_email_address)
            }
            if (TextUtils.isEmpty(password)) {
                binding.passwordText.error = getString(R.string.Please_enter_valid_password_address)
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
}
package com.asimodabas.uni_chat.ui.fragment.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentLoginBinding
import com.asimodabas.uni_chat.viewBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
        
        viewModel.checkCurrentUser()
    }

    private fun setupObservers() {
        viewModel.isUserLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                val action = LoginFragmentDirections.actionLoginFragmentToSecondFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                val action = LoginFragmentDirections.actionLoginFragmentToSecondFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loginButton.isEnabled = !isLoading
        }

        viewModel.shouldClearFieldErrors.observe(viewLifecycleOwner) { shouldClear ->
            if (shouldClear) {
                clearFieldErrors()
            }
        }

        viewModel.fieldErrors.observe(viewLifecycleOwner) { errors ->
            showFieldErrors(errors)
        }

        viewModel.validationError.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailText.text.toString().trim()
            val password = binding.passwordText.text.toString().trim()

            viewModel.processLogin(email, password)
        }

        binding.textViewForgetPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            findNavController().navigate(action)
        }

        binding.signupButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToCreateFragment()
            findNavController().navigate(action)
        }
    }

    private fun clearFieldErrors() {
        binding.emailText.error = null
        binding.passwordText.error = null
    }

    private fun showFieldErrors(errors: Map<String, String>) {
        errors["email"]?.let { 
            binding.emailText.error = getString(R.string.Please_enter_valid_email_address)
        }
        errors["password"]?.let { 
            binding.passwordText.error = getString(R.string.Please_enter_valid_password_address) 
        }
    }
}
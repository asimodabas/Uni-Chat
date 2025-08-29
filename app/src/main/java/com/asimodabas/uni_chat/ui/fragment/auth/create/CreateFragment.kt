package com.asimodabas.uni_chat.ui.fragment.auth.create

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentCreateBinding
import com.asimodabas.uni_chat.viewBinding

class CreateFragment : Fragment(R.layout.fragment_create) {

    private val binding by viewBinding(FragmentCreateBinding::bind)
    private val viewModel: CreateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.registrationSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                val action = CreateFragmentDirections.actionCreateFragmentToSecondFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.createButton.isEnabled = !isLoading
        }

        viewModel.clearFields.observe(viewLifecycleOwner) { shouldClear ->
            if (shouldClear) {
                binding.emailEditText.setText("")
                binding.nameEditText.setText("")
                binding.surnameEditText.setText("")
                binding.passwordEditText.setText("")
            }
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
        binding.createButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val name = binding.nameEditText.text.toString().trim()
            val surname = binding.surnameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            viewModel.processUserRegistration(email, name, surname, password)
        }
    }

    private fun clearFieldErrors() {
        binding.emailEditText.error = null
        binding.nameEditText.error = null
        binding.surnameEditText.error = null
        binding.passwordEditText.error = null
    }

    private fun showFieldErrors(errors: Map<String, String>) {
        errors["email"]?.let { binding.emailEditText.error = it }
        errors["name"]?.let { binding.nameEditText.error = it }
        errors["surname"]?.let { binding.surnameEditText.error = it }
        errors["password"]?.let { binding.passwordEditText.error = it }
    }
}
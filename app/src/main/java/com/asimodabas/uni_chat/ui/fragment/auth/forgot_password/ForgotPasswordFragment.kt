package com.asimodabas.uni_chat.ui.fragment.auth.forgot_password


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentForgotPasswordBinding
import com.asimodabas.uni_chat.viewBinding

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.linearLayout.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
            binding.buttonSend.isEnabled = !isLoading
        }

        viewModel.success.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(
                    requireContext(),
                    R.string.confirmation_email_sent,
                    Toast.LENGTH_SHORT
                ).show()
                val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.validationError.observe(viewLifecycleOwner) { validationError ->
            validationError?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {

        binding.buttonSend.setOnClickListener {
            val email = binding.editTextEMail.text.toString()
            viewModel.processForgotPassword(email)
        }
    }
}
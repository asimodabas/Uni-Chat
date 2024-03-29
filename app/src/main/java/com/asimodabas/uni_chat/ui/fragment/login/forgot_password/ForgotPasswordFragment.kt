package com.asimodabas.uni_chat.ui.fragment.login.forgot_password


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentForgotPasswordBinding
import com.asimodabas.uni_chat.viewBinding

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ForgotPasswordViewModel::class.java]

        binding.buttonSend.setOnClickListener {
            if (binding.editTextEMail.text.isNotEmpty()) {
                val email = binding.editTextEMail.text.toString()
                viewModel.forgotPassword(email)
                observeData()
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.please_enter_your_email_adress,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun observeData() {
        viewModel.animation.observe(viewLifecycleOwner) { animation ->
            animation?.let {
                if (it) {
                    binding.linearLayout.visibility = View.INVISIBLE
                } else {
                    binding.linearLayout.visibility = View.VISIBLE
                }
            }
        }
        viewModel.success.observe(viewLifecycleOwner) { success ->
            success?.let {
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        R.string.confirmation_email_sent,
                        Toast.LENGTH_SHORT
                    ).show()
                    val action =
                        ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                    findNavController().navigate(action)
                }
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.asimodabas.uni_chat.ui.fragment.jobs.language

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentLanguageBinding
import com.asimodabas.uni_chat.model.NavigationType
import com.asimodabas.uni_chat.viewBinding

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private val binding by viewBinding(FragmentLanguageBinding::bind)
    private val viewModel: LanguageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.navigationEvent.observe(viewLifecycleOwner) { navigationEvent ->
            navigationEvent?.let { (navigationType, chatId) ->
                val action = when (navigationType) {
                    NavigationType.CHAT ->
                        LanguageFragmentDirections.actionLanguageFragmentToChatFragment(chatId)

                    NavigationType.MEDIA_CHAT ->
                        LanguageFragmentDirections.actionLanguageFragmentToMediaChatFragment(chatId)
                }
                findNavController().navigate(action)
                viewModel.resetNavigation()
            }
        }
    }

    private fun setupClickListeners() {
        binding.languageEnglishMediaButton.setOnClickListener {
            viewModel.handleLanguageEnglishMediaClick()
        }
        binding.languageEnglishMessageButton.setOnClickListener {
            viewModel.handleLanguageEnglishMessageClick()
        }
        binding.deutschMediaButton.setOnClickListener {
            viewModel.handleDeutschMediaClick()
        }
        binding.deutschMessageButton.setOnClickListener {
            viewModel.handleDeutschMessageClick()
        }
        binding.frenchMediaButton.setOnClickListener {
            viewModel.handleFrenchMediaClick()
        }
        binding.frenchMessageButton.setOnClickListener {
            viewModel.handleFrenchMessageClick()
        }
        binding.russianMediaButton.setOnClickListener {
            viewModel.handleRussianMediaClick()
        }
        binding.russianMessageButton.setOnClickListener {
            viewModel.handleRussianMessageClick()
        }
        binding.japaneseMediaButton.setOnClickListener {
            viewModel.handleJapaneseMediaClick()
        }
        binding.japaneseMessageButton.setOnClickListener {
            viewModel.handleJapaneseMessageClick()
        }
    }
}
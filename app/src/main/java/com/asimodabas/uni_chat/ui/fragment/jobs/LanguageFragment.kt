package com.asimodabas.uni_chat.ui.fragment.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentLanguageBinding
import com.asimodabas.uni_chat.viewBinding

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private val binding by viewBinding(FragmentLanguageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//English
        binding.languageEnglishMediaButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToMediaChatFragment(
                    24
                )
            findNavController().navigate(action)
        }

        binding.languageEnglishMessageButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToChatFragment(
                    24
                )
            findNavController().navigate(action)
        }
//Deutsch
        binding.deutschMediaButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToMediaChatFragment(
                    25
                )
            findNavController().navigate(action)
        }

        binding.deutschMessageButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToChatFragment(
                    25
                )
            findNavController().navigate(action)
        }
//French
        binding.frenchMediaButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToMediaChatFragment(
                    26
                )
            findNavController().navigate(action)
        }

        binding.frenchMessageButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToChatFragment(
                    26
                )
            findNavController().navigate(action)
        }
//Russian
        binding.russianMediaButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToMediaChatFragment(
                    27
                )
            findNavController().navigate(action)
        }

        binding.russianMessageButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToChatFragment(
                    27
                )
            findNavController().navigate(action)
        }
//Japanese
        binding.japaneseMediaButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToMediaChatFragment(
                    28
                )
            findNavController().navigate(action)
        }

        binding.japaneseMessageButton.setOnClickListener {
            val action =
                LanguageFragmentDirections.actionLanguageFragmentToChatFragment(
                    28
                )
            findNavController().navigate(action)
        }
    }
}
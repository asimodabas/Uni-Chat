package com.asimodabas.uni_chat.ui.fragment.second

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentSecondBinding
import com.asimodabas.uni_chat.viewBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding by viewBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//Public
        binding.PublicButton.setOnClickListener {
            val action =
                SecondFragmentDirections.actionSecondFragmentToChatFragment(
                    0
                )
            findNavController().navigate(action)
        }
//Engineer
        binding.engineerButton.setOnClickListener {
            val action =
                SecondFragmentDirections.actionSecondFragmentToEngineerFragment()
            findNavController().navigate(action)
        }
//Teacher
        binding.teacherButton.setOnClickListener {
            val action =
                SecondFragmentDirections.actionSecondFragmentToTeacherFragment()
            findNavController().navigate(action)
        }
//Health
        binding.healthButton.setOnClickListener {
            val action =
                SecondFragmentDirections.actionSecondFragmentToHealthFragment()
            findNavController().navigate(action)
        }
//Language
        binding.languageButton.setOnClickListener {
            val action =
                SecondFragmentDirections.actionSecondFragmentToLanguageFragment()
            findNavController().navigate(action)
        }
    }
}
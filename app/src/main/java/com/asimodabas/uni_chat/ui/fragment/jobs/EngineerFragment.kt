package com.asimodabas.uni_chat.ui.fragment.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentEngineerBinding
import com.asimodabas.uni_chat.viewBinding

class EngineerFragment : Fragment(R.layout.fragment_engineer) {

    private val binding by viewBinding(FragmentEngineerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//Computer
        binding.computerMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    1
                )
            findNavController().navigate(action)
        }

        binding.computerMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    1
                )
            findNavController().navigate(action)
        }
//Chemical
        binding.chemicalMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    2
                )
            findNavController().navigate(action)
        }

        binding.chemicalMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    2
                )
            findNavController().navigate(action)
        }
//Industry
        binding.industryMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    3
                )
            findNavController().navigate(action)
        }

        binding.industryMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    3
                )
            findNavController().navigate(action)
        }
//Build
        binding.buildMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    4
                )
            findNavController().navigate(action)
        }

        binding.buildMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    4
                )
            findNavController().navigate(action)
        }
//Food
        binding.foodMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    5
                )
            findNavController().navigate(action)
        }

        binding.foodMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    5
                )
            findNavController().navigate(action)
        }
//Electric
        binding.electricMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    6
                )
            findNavController().navigate(action)
        }

        binding.electricMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    6
                )
            findNavController().navigate(action)
        }
//Machine
        binding.machineMediaButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(
                    7
                )
            findNavController().navigate(action)
        }

        binding.machineMessageButton.setOnClickListener {
            val action =
                EngineerFragmentDirections.actionEngineerFragmentToChatFragment(
                    7
                )
            findNavController().navigate(action)
        }
    }
}
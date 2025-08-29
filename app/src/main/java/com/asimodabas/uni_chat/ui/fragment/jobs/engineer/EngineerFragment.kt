package com.asimodabas.uni_chat.ui.fragment.jobs.engineer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentEngineerBinding
import com.asimodabas.uni_chat.model.NavigationType
import com.asimodabas.uni_chat.viewBinding

class EngineerFragment : Fragment(R.layout.fragment_engineer) {

    private val binding by viewBinding(FragmentEngineerBinding::bind)
    private val viewModel: EngineerViewModel by viewModels()

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
                        EngineerFragmentDirections.actionEngineerFragmentToChatFragment(chatId)

                    NavigationType.MEDIA_CHAT ->
                        EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(chatId)
                }
                findNavController().navigate(action)
                viewModel.resetNavigation()
            }
        }
    }

    private fun setupClickListeners() {
        binding.computerMediaButton.setOnClickListener {
            viewModel.handleComputerMediaClick()
        }
        binding.computerMessageButton.setOnClickListener {
            viewModel.handleComputerMessageClick()
        }
        binding.chemicalMediaButton.setOnClickListener {
            viewModel.handleChemicalMediaClick()
        }
        binding.chemicalMessageButton.setOnClickListener {
            viewModel.handleChemicalMessageClick()
        }
        binding.industryMediaButton.setOnClickListener {
            viewModel.handleIndustryMediaClick()
        }
        binding.industryMessageButton.setOnClickListener {
            viewModel.handleIndustryMessageClick()
        }
        binding.buildMediaButton.setOnClickListener {
            viewModel.handleBuildMediaClick()
        }
        binding.buildMessageButton.setOnClickListener {
            viewModel.handleBuildMessageClick()
        }
        binding.foodMediaButton.setOnClickListener {
            viewModel.handleFoodMediaClick()
        }
        binding.foodMessageButton.setOnClickListener {
            viewModel.handleFoodMessageClick()
        }
        binding.electricMediaButton.setOnClickListener {
            viewModel.handleElectricMediaClick()
        }
        binding.electricMessageButton.setOnClickListener {
            viewModel.handleElectricMessageClick()
        }
        binding.machineMediaButton.setOnClickListener {
            viewModel.handleMachineMediaClick()
        }
        binding.machineMessageButton.setOnClickListener {
            viewModel.handleMachineMessageClick()
        }
    }
}
package com.asimodabas.uni_chat.ui.fragment.jobs.health

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentHealthBinding
import com.asimodabas.uni_chat.model.NavigationType
import com.asimodabas.uni_chat.viewBinding

class HealthFragment : Fragment(R.layout.fragment_health) {

    private val binding by viewBinding(FragmentHealthBinding::bind)
    private val viewModel: HealthViewModel by viewModels()

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
                        HealthFragmentDirections.actionHealthFragmentToChatFragment(chatId)

                    NavigationType.MEDIA_CHAT ->
                        HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(chatId)
                }
                findNavController().navigate(action)
                viewModel.resetNavigation()
            }
        }
    }

    private fun setupClickListeners() {
        binding.medicineMediaButton.setOnClickListener {
            viewModel.handleMedicineMediaClick()
        }
        binding.medicineMessageButton.setOnClickListener {
            viewModel.handleMedicineMessageClick()
        }
        binding.dentistMediaButton.setOnClickListener {
            viewModel.handleDentistMediaClick()
        }
        binding.dentistMessageButton.setOnClickListener {
            viewModel.handleDentistMessageClick()
        }
        binding.nurseMediaButton.setOnClickListener {
            viewModel.handleNurseMediaClick()
        }
        binding.nurseMessageButton.setOnClickListener {
            viewModel.handleNurseMessageClick()
        }
        binding.psychologyMediaButton.setOnClickListener {
            viewModel.handlePsychologyMediaClick()
        }
        binding.psychologyMessageButton.setOnClickListener {
            viewModel.handlePsychologyMessageClick()
        }
        binding.pharmacyMediaButton.setOnClickListener {
            viewModel.handlePharmacyMediaClick()
        }
        binding.pharmacyMessageButton.setOnClickListener {
            viewModel.handlePharmacyMessageClick()
        }
        binding.veterinaryMediaButton.setOnClickListener {
            viewModel.handleVeterinaryMediaClick()
        }
        binding.veterinaryMessageButton.setOnClickListener {
            viewModel.handleVeterinaryMessageClick()
        }
        binding.dieteticsMediaButton.setOnClickListener {
            viewModel.handleDieteticsMediaClick()
        }
        binding.dieteticsMessageButton.setOnClickListener {
            viewModel.handleDieteticsMessageClick()
        }
        binding.rehabilitationMediaButton.setOnClickListener {
            viewModel.handleRehabilitationMediaClick()
        }
        binding.rehabilitationMessageButton.setOnClickListener {
            viewModel.handleRehabilitationMessageClick()
        }
    }
}
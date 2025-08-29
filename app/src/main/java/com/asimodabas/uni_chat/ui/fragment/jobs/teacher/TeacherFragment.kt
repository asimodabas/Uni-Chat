package com.asimodabas.uni_chat.ui.fragment.jobs.teacher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentTeacherBinding
import com.asimodabas.uni_chat.model.NavigationType
import com.asimodabas.uni_chat.viewBinding

class TeacherFragment : Fragment(R.layout.fragment_teacher) {

    private val binding by viewBinding(FragmentTeacherBinding::bind)
    private val viewModel: TeacherViewModel by viewModels()

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
                        TeacherFragmentDirections.actionTeacherFragmentToChatFragment(chatId)

                    NavigationType.MEDIA_CHAT ->
                        TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(chatId)
                }
                findNavController().navigate(action)
                viewModel.resetNavigation()
            }
        }
    }

    private fun setupClickListeners() {
        binding.physicsMediaButton.setOnClickListener {
            viewModel.handlePhysicsMediaClick()
        }
        binding.physicsMessageButton.setOnClickListener {
            viewModel.handlePhysicsMessageClick()
        }
        binding.literatureMediaButton.setOnClickListener {
            viewModel.handleLiteratureMediaClick()
        }
        binding.literatureMessageButton.setOnClickListener {
            viewModel.handleLiteratureMessageClick()
        }
        binding.chemicalTeacherMediaButton.setOnClickListener {
            viewModel.handleChemicalTeacherMediaClick()
        }
        binding.chemicalTeacherMessageButton.setOnClickListener {
            viewModel.handleChemicalTeacherMessageClick()
        }
        binding.mathsMediaButton.setOnClickListener {
            viewModel.handleMathsMediaClick()
        }
        binding.mathsMessageButton.setOnClickListener {
            viewModel.handleMathsMessageClick()
        }
        binding.biologyMediaButton.setOnClickListener {
            viewModel.handleBiologyMediaClick()
        }
        binding.biologyMessageButton.setOnClickListener {
            viewModel.handleBiologyMessageClick()
        }
        binding.englishMediaButton.setOnClickListener {
            viewModel.handleEnglishMediaClick()
        }
        binding.englishMessageButton.setOnClickListener {
            viewModel.handleEnglishMessageClick()
        }
        binding.geographyMediaButton.setOnClickListener {
            viewModel.handleGeographyMediaClick()
        }
        binding.geographyMessageButton.setOnClickListener {
            viewModel.handleGeographyMessageClick()
        }
        binding.historyMediaButton.setOnClickListener {
            viewModel.handleHistoryMediaClick()
        }
        binding.historyMessageButton.setOnClickListener {
            viewModel.handleHistoryMessageClick()
        }
    }
}
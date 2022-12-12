package com.asimodabas.uni_chat.ui.fragment.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentTeacherBinding
import com.asimodabas.uni_chat.viewBinding

class TeacherFragment : Fragment(R.layout.fragment_teacher) {

    private val binding by viewBinding(FragmentTeacherBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//Physics
        binding.physicsMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    8
                )
            findNavController().navigate(action)
        }

        binding.physicsMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    8
                )
            findNavController().navigate(action)
        }
//Literature
        binding.literatureMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    9
                )
            findNavController().navigate(action)
        }

        binding.literatureMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    9
                )
            findNavController().navigate(action)
        }
//Chemical
        binding.chemicalTeacherMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    10
                )
            findNavController().navigate(action)
        }

        binding.chemicalTeacherMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    10
                )
            findNavController().navigate(action)
        }
//Maths
        binding.mathsMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    11
                )
            findNavController().navigate(action)
        }

        binding.mathsMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    11
                )
            findNavController().navigate(action)
        }
//Biology
        binding.biologyMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    12
                )
            findNavController().navigate(action)
        }

        binding.biologyMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    12
                )
            findNavController().navigate(action)
        }
//English
        binding.englishMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    13
                )
            findNavController().navigate(action)
        }

        binding.englishMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    13
                )
            findNavController().navigate(action)
        }
//Geography
        binding.geographyMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    14
                )
            findNavController().navigate(action)
        }

        binding.geographyMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    14
                )
            findNavController().navigate(action)
        }
//History
        binding.historyMediaButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToMediaChatFragment(
                    15
                )
            findNavController().navigate(action)
        }

        binding.historyMessageButton.setOnClickListener {
            val action =
                TeacherFragmentDirections.actionTeacherFragmentToChatFragment(
                    15
                )
            findNavController().navigate(action)
        }
    }
}
package com.asimodabas.uni_chat.ui.fragment.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.databinding.FragmentHealthBinding
import com.asimodabas.uni_chat.viewBinding

class HealthFragment : Fragment(R.layout.fragment_health) {

    private val binding by viewBinding(FragmentHealthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//Medicine
        binding.medicineMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    16
                )
            findNavController().navigate(action)
        }

        binding.medicineMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    16
                )
            findNavController().navigate(action)
        }
//Dentist
        binding.dentistMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    17
                )
            findNavController().navigate(action)
        }

        binding.dentistMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    17
                )
            findNavController().navigate(action)
        }
//Nurse
        binding.nurseMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    18
                )
            findNavController().navigate(action)
        }

        binding.nurseMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    18
                )
            findNavController().navigate(action)
        }
//Psychology
        binding.psychologyMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    19
                )
            findNavController().navigate(action)
        }

        binding.psychologyMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    19
                )
            findNavController().navigate(action)
        }
//Pharmacy
        binding.pharmacyMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    20
                )
            findNavController().navigate(action)
        }

        binding.pharmacyMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    20
                )
            findNavController().navigate(action)
        }
//Veterinary
        binding.veterinaryMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    21
                )
            findNavController().navigate(action)
        }

        binding.veterinaryMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    21
                )
            findNavController().navigate(action)
        }
//Dietetics
        binding.dieteticsMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    22
                )
            findNavController().navigate(action)
        }

        binding.dieteticsMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    22
                )
            findNavController().navigate(action)
        }
//Rehabilitation
        binding.rehabilitationMediaButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToMediaChatFragment(
                    23
                )
            findNavController().navigate(action)
        }

        binding.rehabilitationMessageButton.setOnClickListener {
            val action =
                HealthFragmentDirections.actionHealthFragmentToChatFragment(
                    23
                )
            findNavController().navigate(action)
        }
    }
}
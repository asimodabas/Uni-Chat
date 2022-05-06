package com.asimodabas.uni_chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.asimodabas.uni_chat.databinding.FragmentChatBinding
import com.asimodabas.uni_chat.databinding.FragmentEngineerBinding
import com.asimodabas.uni_chat.databinding.FragmentSecondBinding

class EngineerFragment : Fragment() {

    private var _binding: FragmentEngineerBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEngineerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.computerMediaButton.setOnClickListener {
            val action = EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(1)
            findNavController().navigate(action)
        }

        binding.computerMessageButton.setOnClickListener {
            val action = EngineerFragmentDirections.actionEngineerFragmentToChatFragment(1)
            findNavController().navigate(action)
        }

        binding.chemicalMessageButton.setOnClickListener {
            val action = EngineerFragmentDirections.actionEngineerFragmentToChatFragment(2)
            findNavController().navigate(action)
        }

        binding.chemicalMediaButton.setOnClickListener {
            val action = EngineerFragmentDirections.actionEngineerFragmentToMediaChatFragment(2)
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
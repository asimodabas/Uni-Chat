package com.asimodabas.uni_chat.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asimodabas.uni_chat.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
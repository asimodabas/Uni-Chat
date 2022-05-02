package com.asimodabas.uni_chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.asimodabas.uni_chat.databinding.FragmentChatBinding
import com.asimodabas.uni_chat.databinding.FragmentEngineerBinding
import com.asimodabas.uni_chat.databinding.FragmentSecondBinding

class EngineerFragment : Fragment() {

    private var _binding: FragmentEngineerBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobArrayList: ArrayList<Jobs>
    private lateinit var adapter: JobsAdapter

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

        binding.engineerRecyclerView.setHasFixedSize(true)
        binding.engineerRecyclerView.layoutManager= LinearLayoutManager(requireContext())

        val j1 = Jobs(1,"Bilgisayar Mühendisi","computer")
        val j2 = Jobs(2,"Elektrik Mühendisi","electrical")

        jobArrayList = ArrayList<Jobs>()
        jobArrayList.add(j1)
        jobArrayList.add(j2)

        adapter = JobsAdapter(requireContext(),jobArrayList)

        binding.engineerRecyclerView.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
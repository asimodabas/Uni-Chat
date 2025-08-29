package com.asimodabas.uni_chat.ui.fragment.media_chat

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.adapter.MediaRecyclerAdapter
import com.asimodabas.uni_chat.databinding.FragmentMediaChatBinding
import com.asimodabas.uni_chat.viewBinding

class MediaChatFragment : Fragment(R.layout.fragment_media_chat) {

    private val binding by viewBinding(FragmentMediaChatBinding::bind)
    private val viewModel: MediaChatViewModel by viewModels()
    private lateinit var mediaAdapter: MediaRecyclerAdapter
    private val args: MediaChatFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()

        viewModel.getMediaMessages(args.departmentId)
    }

    private fun setupRecyclerView() {
        binding.mediaChatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mediaAdapter = MediaRecyclerAdapter()
        binding.mediaChatRecyclerView.adapter = mediaAdapter
    }

    private fun setupObservers() {
        viewModel.medias.observe(viewLifecycleOwner) { medias ->
            mediaAdapter.medias = medias
            mediaAdapter.notifyDataSetChanged()
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.isEmpty.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                Toast.makeText(requireContext(), R.string.no_message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.logoutSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                val action = MediaChatFragmentDirections.actionMediaChatFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun setupClickListeners() {
        binding.floatingActionButton.setOnClickListener {
            val action = MediaChatFragmentDirections.actionMediaChatFragmentToUploadMediaFragment(
                args.departmentId
            )
            findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lagout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.signout) {
            viewModel.signOut()
        }
        return super.onOptionsItemSelected(item)
    }
}
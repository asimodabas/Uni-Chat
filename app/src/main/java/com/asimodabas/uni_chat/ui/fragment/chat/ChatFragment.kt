package com.asimodabas.uni_chat.ui.fragment.chat

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
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.adapter.ChatRecyclerAdapter
import com.asimodabas.uni_chat.databinding.FragmentChatBinding
import com.asimodabas.uni_chat.viewBinding

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val binding by viewBinding(FragmentChatBinding::bind)
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: ChatRecyclerAdapter
    private val args: ChatFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()

        viewModel.getChatMessages(args.chatType)
    }

    private fun setupRecyclerView() {
        adapter = ChatRecyclerAdapter()
        binding.chatRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.chats.observe(viewLifecycleOwner) { chats ->
            adapter.chats = chats
            adapter.notifyDataSetChanged()
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.messageSent.observe(viewLifecycleOwner) { sent ->
            if (sent) {
                binding.chatText.setText("")
            }
        }

        viewModel.isEmpty.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                Toast.makeText(requireContext(), R.string.no_message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.logoutSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                val action = ChatFragmentDirections.actionChatFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.sendButton.isEnabled = !isLoading
        }

        viewModel.validationError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.sendButton.setOnClickListener {
            val chatText = binding.chatText.text.toString()
            viewModel.processSendMessage(args.chatType, chatText)
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
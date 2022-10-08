package com.asimodabas.uni_chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.model.UniChat
import com.google.firebase.auth.FirebaseAuth

class ChatRecyclerAdapter : RecyclerView.Adapter<ChatRecyclerAdapter.ChatHolder>() {

    private val VİEW_TYPE_MESSAGE_SEND = 1
    private val VİEW_TYPE_MESSAGE_RECEIVED = 2

    class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<UniChat>() {
        override fun areItemsTheSame(oldItem: UniChat, newItem: UniChat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UniChat, newItem: UniChat): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var chats: List<UniChat>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun getItemViewType(position: Int): Int {
        val chat = chats.get(position)
        if (chat.user == FirebaseAuth.getInstance().currentUser?.email.toString()) {
            return VİEW_TYPE_MESSAGE_SEND
        } else {
            return VİEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        if (viewType == VİEW_TYPE_MESSAGE_RECEIVED) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_row_chat, parent, false)
            return ChatHolder(view)

        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_row_chat_right, parent, false)
            return ChatHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.chatTextRecycler)
        textView.text = "${chats.get(position).user}: ${chats.get(position).text}"
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}
package com.asimodabas.uni_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ChatRecyclerAdapter : RecyclerView.Adapter<ChatRecyclerAdapter.ChatHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_chat, parent, false)
        return ChatHolder(view)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.chatTextRecycler)
        textView.text = "${chats.get(position).user} :${chats.get(position).text}"
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}
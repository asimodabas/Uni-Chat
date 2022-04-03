package com.asimodabas.uni_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ChatRecyclerAdapter : RecyclerView.Adapter<ChatRecyclerAdapter.ChatHolder>(){

    class ChatHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    private val diffUtil=object :DiffUtil.ItemCallback<UniChat>(){
        override fun areItemsTheSame(oldItem: UniChat, newItem: UniChat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UniChat, newItem: UniChat): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var chats :List<UniChat>

    get() =recyclerListDiffer.currentList
    set(value)=recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}
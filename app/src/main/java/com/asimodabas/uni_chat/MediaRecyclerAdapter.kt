package com.asimodabas.uni_chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asimodabas.uni_chat.databinding.RecyclerRowMediaBinding

class MediaRecyclerAdapter(private val mediaList:ArrayList<UniMedia>):RecyclerView.Adapter<MediaRecyclerAdapter.MediaHolder>() {

    class MediaHolder(val binding: RecyclerRowMediaBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaHolder {

        val binding = RecyclerRowMediaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MediaHolder(binding)

    }

    override fun onBindViewHolder(holder: MediaHolder, position: Int) {
        holder.binding.mediaEmailEditText.text = mediaList.get(position).email

    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

}
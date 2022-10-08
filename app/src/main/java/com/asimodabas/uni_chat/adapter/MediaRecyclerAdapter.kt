package com.asimodabas.uni_chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asimodabas.uni_chat.R
import com.asimodabas.uni_chat.model.UniMedia
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class MediaRecyclerAdapter : RecyclerView.Adapter<MediaRecyclerAdapter.MediaHolder>() {

    private val VİEW_TYPE_MESSAGE_SEND = 1
    private val VİEW_TYPE_MESSAGE_RECEIVED = 2

    class MediaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<UniMedia>() {
        override fun areItemsTheSame(oldItem: UniMedia, newItem: UniMedia): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UniMedia, newItem: UniMedia): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)


    var medias: List<UniMedia>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaHolder {
        if (viewType == VİEW_TYPE_MESSAGE_RECEIVED) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_row_media, parent, false)
            return MediaHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_row_media_right, parent, false)
            return MediaHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MediaHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.mediaEmailEditText)
        textView.text = "${medias.get(position).email}"

        val imageView = holder.itemView.findViewById<ImageView>(R.id.mediaImageView)
        Picasso.get().load(medias.get(position).downloadUrl).into(imageView)
    }

    override fun getItemViewType(position: Int): Int {
        val media = medias.get(position)

        if (media.email == FirebaseAuth.getInstance().currentUser?.email.toString()) {

            return VİEW_TYPE_MESSAGE_SEND

        } else {

            return VİEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return medias.size
    }
}
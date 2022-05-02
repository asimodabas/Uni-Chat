package com.asimodabas.uni_chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobsAdapter(val mContext : Context,val jobLists:List<Jobs>) : RecyclerView.Adapter<JobsAdapter.CardConservative>() {

    inner class CardConservative (view:View):RecyclerView.ViewHolder(view){

        var engineerImageView : ImageView

        var engineerTextView : TextView

        init {
            engineerImageView = view.findViewById(R.id.engineerImageView)
            engineerTextView = view.findViewById(R.id.engineerTextView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardConservative {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design,parent,false)
        return CardConservative(design)
    }

    override fun onBindViewHolder(holder: CardConservative, position: Int) {
        val job = jobLists[position]

        holder.engineerTextView.text = job.jobName
        holder.engineerImageView.setImageResource(mContext.resources.getIdentifier(job.imageName,"drawable",mContext.packageName))

    }

    override fun getItemCount(): Int {
        return jobLists.size
    }

}
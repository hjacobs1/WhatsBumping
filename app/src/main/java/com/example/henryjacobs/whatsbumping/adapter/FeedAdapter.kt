package com.example.henryjacobs.whatsbumping.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.henryjacobs.whatsbumping.R
import com.example.henryjacobs.whatsbumping.data.Post

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    var feedList = mutableListOf<Post>()

    val context : Context
    constructor(context: Context):super(){
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.row_feed, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}
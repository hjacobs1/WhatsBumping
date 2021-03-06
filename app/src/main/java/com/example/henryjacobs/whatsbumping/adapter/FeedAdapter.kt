package com.example.henryjacobs.whatsbumping.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.henryjacobs.whatsbumping.R
import com.example.henryjacobs.whatsbumping.data.Post
import kotlinx.android.synthetic.main.row_feed.view.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    var postList = mutableListOf<Post>()
    var postKeys = mutableListOf<String>()

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
        return postList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var time = System.currentTimeMillis()
        var post = postList[position]
        holder.tvTrack.text = post.track
        holder.tvArtist.text = post.artist
        holder.tvTimestamp.text = ((time - post.timeStamp.toLong())/60000).toString() + context.getString(R.string.min_ago)
        holder.tvUsername.text = post.userName
        Glide.with(context).load(post.coverPhotoURL)
            .into(holder.imgCover)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvTrack = itemView.tvTrack
        var imgCover = itemView.imgCover
        var tvArtist = itemView.tvArtist
        var tvTimestamp = itemView.tvTimestamp
        var tvUsername = itemView.tvUsername
    }

    fun addPost(post: Post, key: String) {

        postList.add(post)
        postKeys.add(key)
        notifyDataSetChanged()
    }

    fun removePostByKey(key: String) {
        val index = postKeys.indexOf(key)
        if (index != -1){
            postList.removeAt(index)
            postKeys.removeAt(index)
            notifyItemRemoved(index)
        }
    }


    
}
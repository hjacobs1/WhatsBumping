package com.example.henryjacobs.whatsbumping.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        var post = postList[position]
        holder.tvTrack.text = post.track
        holder.tvArtist.text = post.artist
        holder.tvTimestamp.text = post.timeStamp
        holder.tvUsername.text = post.userName
        //TODO: set image view content (imgCover)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvTrack = itemView.tvTrack
        var imgCover = itemView.imgCover
        var tvArtist = itemView.tvArtist
        var tvTimestamp = itemView.tvTimestamp
        var tvUsername = itemView.tvUsername
    }

//    fun setContent(allPosts: List<Post>){
//        postList = allPosts.toMutableList()
//        notifyDataSetChanged()
//    }

    fun addPost(post: Post, key: String) {

        postList.add(post)
        postKeys.add(key)
        notifyDataSetChanged()
    }



    
}
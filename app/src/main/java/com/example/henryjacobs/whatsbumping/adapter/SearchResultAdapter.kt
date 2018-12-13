package com.example.henryjacobs.whatsbumping.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.henryjacobs.whatsbumping.FeedActivity
import com.example.henryjacobs.whatsbumping.R
import com.example.henryjacobs.whatsbumping.data.Post
import com.example.henryjacobs.whatsbumping.data.SearchResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.row_add_post_search.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SearchResultAdapter: RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{
    var searchResults = mutableListOf<SearchResult>()

    var context: Context

    constructor(context: Context, results: List<SearchResult>) : super() {
        this.context = context
        this.searchResults.addAll(results)
    }

    constructor(context: Context) : super(){
        this.context = context
    }

    // what one item in the recycler view looks like
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.row_add_post_search, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = searchResults[position]
        holder.tvSearchTrack.text = result.track

        holder.tvSearchArtist.text = result.artist

        Glide.with(context).load(result.coverPhotoURL)
            .into(holder.imgSearch)



        holder.itemView.setOnClickListener{
            val time = System.currentTimeMillis().toString()
            var post = Post(context.getString(R.string.uid),context.getString(R.string.ethan), result.track, result.artist, result.coverPhotoURL, time)
            val postCollection = FirebaseFirestore.getInstance().collection("posts")
            postCollection.add(post).addOnSuccessListener {
                Toast.makeText(context, context.getString(R.string.successful_post), Toast.LENGTH_LONG).show()
                var intent = Intent(context,FeedActivity::class.java)
                context.startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(context, context.getString(R.string.failed_post),Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvSearchTrack = itemView.tvSearchTrack
        val tvSearchArtist = itemView.tvSearchArtist
        val imgSearch = itemView.imgSearch
    }

}
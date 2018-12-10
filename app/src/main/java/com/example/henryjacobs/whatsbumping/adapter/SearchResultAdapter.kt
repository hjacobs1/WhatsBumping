package com.example.henryjacobs.whatsbumping.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.henryjacobs.whatsbumping.R
import com.example.henryjacobs.whatsbumping.data.Post
import com.example.henryjacobs.whatsbumping.data.SearchResult
import kotlinx.android.synthetic.main.row_add_post_search.view.*

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

        //TODO use glide library to load in image
//        Glide.with(this@DetailsActivity) .load(
//            (getString(R.string.weather_icon_url) + response.body()?.weather?.get(0)?.icon + getString(R.string.png_extension)))
//            .into(holder.imgSearch)



        holder.itemView.setOnClickListener{
            //TODO need to send whole object to firebase, we will have a onChangeListener in FeedActivity to catch these changes
//            var intentDetails = Intent(context, DetailsActivity::class.java)
//            intentDetails.putExtra(context.getString(R.string.key_city), cityItems[holder.adapterPosition].cityName)
//            context.startActivity(intentDetails)
        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvSearchTrack = itemView.tvSearchTrack
        val tvSearchArtist = itemView.tvSearchArtist
        val imgSearch = itemView.imgSearch
    }

}
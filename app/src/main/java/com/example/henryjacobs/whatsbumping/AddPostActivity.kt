package com.example.henryjacobs.whatsbumping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.henryjacobs.whatsbumping.adapter.SearchResultAdapter
import com.example.henryjacobs.whatsbumping.data.SearchResult
import com.example.henryjacobs.whatsbumping.network.TrackSearchAPI
import kotlinx.android.synthetic.main.activity_add_post.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddPostActivity : AppCompatActivity() {
    private val HOST_URL = "https://api.spotify.com/"
    private val API_KEY = "d8416289a275514ed0b5ab828b1f79e9"

    val adapter = SearchResultAdapter(this)
    lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        var intent = getIntent()
        userName = intent.getStringExtra("name")

        btnSearch.setOnClickListener {
            adapter.searchResults.add(SearchResult(getString(R.string.search_title1), getString(R.string.search_artist1), getString(
                            R.string.search_img_url1)))
            adapter.searchResults.add(SearchResult(getString(R.string.search_title2), getString(R.string.search_artist2), getString(
                            R.string.search_img_url2)))
            searchResultsView.adapter = adapter

            //adapter.notifyDataSetChanged()
//            val retrofit = Retrofit.Builder().
//                baseUrl(HOST_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            val searchAPI = retrofit.create(TrackSearchAPI::class.java)
//
//            val searchResultsCall = searchAPI.getTrackSearchResults(etSearchTrack.text.toString(), "track", "US", "3") // creating the call must be in the onClickListener
//            searchResultsCall.enqueue(object : Callback<JSONArray> {
//                override fun onFailure(call: Call<JSONArray>, t: Throwable) {  // t will be the exception
//                    Toast.makeText(this@AddPostActivity,"Call to API failed: ${t.message}", Toast.LENGTH_LONG).show()
//                }
//
//
//                override fun onResponse(call: Call<JSONArray>, response: Response<JSONArray>) {
//                    val searchResult = response.body()
//
//                    Log.d("API_RESULT", searchResult.toString())
//                }
//            })
        }
    }
}

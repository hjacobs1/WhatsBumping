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

    //TODO have to add handling of spaces in track name search ex: XO TOUR LIiF3 --> XO%20TOUR%20LIiF3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        var intent = getIntent()
        userName = intent.getStringExtra("name")

        btnSearch.setOnClickListener {
            adapter.searchResults.add(SearchResult("She Will", "Lil Wayne", "https://www.billboard.com/files/styles/article_main_image/public/stylus/1174239-Lil-Wayne-Deluxe-Edition-Cover-617x409.jpg"))
            adapter.searchResults.add(SearchResult("She Will Be Loved", "Maroon 5", "https://upload.wikimedia.org/wikipedia/en/b/be/Maroon_5_-_Songs_About_Jane.png"))
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

        //TODO use search button here, get result, set the list based on that
    }

    //TODO need a function that converts JSON result of api call to SearchResult objects, these are used in the adapter
}

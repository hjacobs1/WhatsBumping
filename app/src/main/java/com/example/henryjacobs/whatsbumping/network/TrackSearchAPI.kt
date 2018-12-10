package com.example.henryjacobs.whatsbumping.network
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackSearchAPI {
    @GET("2.0")
    fun getTrackSearchResults(
        @Query("method") method: String,
        @Query("track") track: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String
    ): Call<JSONArray>
}
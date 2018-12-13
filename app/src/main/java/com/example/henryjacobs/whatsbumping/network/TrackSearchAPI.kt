package com.example.henryjacobs.whatsbumping.network
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface TrackSearchAPI {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Authorization: Bearer BQAFW_gSMQmc4I_91Ps6BQBnHHYRqv99kd0FNfKRxmGJzsxfgj62eUH-ji4ed3oP6E4CMW9B2hg4BTmqI7HgnaUhPBY9BApcUX2gJw9ZD3X6bUiP7ZCbU9YrdR3Odp6jf0ImQIe1lM4Pww"
    )
    @GET("v1/search")
    fun getTrackSearchResults(
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("market") market: String,
        @Query("limit") limit: String
    ): Call<JSONArray>
}
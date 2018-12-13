package com.example.henryjacobs.whatsbumping.network

import com.example.henryjacobs.whatsbumping.data.UserResult
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserAPI{
    @Headers("Authorization: Bearer")
    @GET("/v1/me")
    fun getUserResults(): Call<UserResult>
}

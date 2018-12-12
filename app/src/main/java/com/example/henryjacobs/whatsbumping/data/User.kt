package com.example.henryjacobs.whatsbumping.data

// result generated from /json

data class UserResult(val birthdate: String?, val country: String?, val display_name: String?, val email: String?, val external_urls: External_urls?, val followers: Followers?, val href: String?, val id: String?, val images: List<Images1208864268>?, val product: String?, val type: String?, val uri: String?)

data class External_urls(val spotify: String?)

data class Followers(val href: Any?, val total: Number?)

data class Images1208864268(val height: Any?, val url: String?, val width: Any?)

data class User(val name: String, val email: String, val followers: List<String>)
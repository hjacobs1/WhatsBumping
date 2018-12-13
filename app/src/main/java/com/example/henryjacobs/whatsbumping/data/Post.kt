package com.example.henryjacobs.whatsbumping.data

data class Post(
    var userID : String = "",
    var userName : String = "",
    var track: String = "",
    var artist: String = "",
    var coverPhotoURL: String = "",
    var timeStamp: String = ""
)
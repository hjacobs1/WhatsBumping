package com.example.henryjacobs.whatsbumping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henryjacobs.whatsbumping.R
import com.example.henryjacobs.whatsbumping.adapter.FeedAdapter
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    lateinit var userId : String

    var adapter = FeedAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        userId = intent.getStringExtra("userID")
        setForUser()
        pastPosts.adapter = adapter
    }

    fun setForUser(){

    }

}

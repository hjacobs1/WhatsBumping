package com.example.henryjacobs.whatsbumping

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.henryjacobs.whatsbumping.adapter.FeedAdapter
import com.example.henryjacobs.whatsbumping.data.Post
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.app_bar_feed.*
import kotlinx.android.synthetic.main.content_feed.*
import kotlinx.android.synthetic.main.dialogue_follow.*
import kotlinx.android.synthetic.main.dialogue_follow.view.*

class FeedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var adapter = FeedAdapter(this)

    var userId = "18fnc093ksk1"

    //private lateinit var feedAdapter: FeedAdapter
    private lateinit var postsListener: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setSupportActionBar(toolbar)

        var intent = getIntent()
        var userName = intent.getStringExtra("name")

        //TODO need to have a listener for when posts collection in firebase changes. Then need to get the objects from that collection and turn ethem into POST objects and add them to the adapter's list
        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddPostActivity::class.java).putExtra("name", userName))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        feedView.adapter = adapter

        listenForPosts()

    }

    fun listenForPosts(){
        val db = FirebaseFirestore.getInstance()
        val postsCollection = db.collection("posts")

        postsListener = postsCollection.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(querySnapshot: QuerySnapshot?, p1: FirebaseFirestoreException?) {

                if (p1 != null) {  // if there was an error b/c p1 is an error argument
                    Toast.makeText(this@FeedActivity, "Error: ${p1.message}",
                        Toast.LENGTH_LONG).show()
                    return
                }

                // check all items in snapshot of db and see their status
                for (docChange in querySnapshot!!.getDocumentChanges()) {
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            val post = docChange.document.toObject(Post::class.java)
                            adapter.addPost(post, docChange.document.id)
                        }
                        DocumentChange.Type.MODIFIED -> {

                        }
                        DocumentChange.Type.REMOVED -> {

                        }
                    }
                }
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            //R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                var intent = Intent(this@FeedActivity,UserActivity::class.java)
                intent.putExtra("userID",userId)
                startActivity(intent)
            }
            R.id.nav_follow -> {
                drawer_layout.closeDrawer(GravityCompat.START)
                openFollowDialogue()
            }
            R.id.nav_post -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun openFollowDialogue(){
        var builder = AlertDialog.Builder(this)
        var bview = layoutInflater.inflate(R.layout.dialogue_follow, null)
        builder.setTitle("Follow User")
        builder.setView(bview)
        var followAlert = builder.create()
        setClickListeners(bview,followAlert)
        followAlert.show()
    }

    private fun setClickListeners(bview: View,alert: AlertDialog) {
        bview.btnCancel.setOnClickListener {
            if (bview.etName.text.toString() != "") {
                followUser(bview.etName.text.toString())
            }
            alert.dismiss()
        }
        bview.btnFollow.setOnClickListener {
            followUser(bview.etName.text.toString())
            alert.dismiss()
        }
    }

    private fun followUser(name: String){
        //TODO implement follow user
        val dbin = FirebaseFirestore.getInstance().collection("users").get()
        println(dbin)
    }
}

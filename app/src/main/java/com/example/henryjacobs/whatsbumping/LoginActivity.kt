package com.example.henryjacobs.whatsbumping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.spotify.sdk.android.authentication.AuthenticationRequest
import kotlinx.android.synthetic.main.activity_login.*
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import java.io.IOException


class LoginActivity : AppCompatActivity() {
    //SPOTIFY STUFF
    //var api = SpotifyApi.Builder().set
    val REQUEST_CODE = 1337
    val REDIRECT_URI = "myapp://authresponse"
    val CLIENT_ID = "413f92986b554b8f91d2c0c407ca340b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun registerClick(v: View) {
        if (!isFormValid()) {
            return
        }
//        // needs a callback method so that you know this connection and msg to Firebase was successful or not
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
//            etEmail.text.toString(), etPassword.text.toString()
//        ).addOnSuccessListener {
//            val user = it.user // firebase user
//            user.updateProfile(
//                UserProfileChangeRequest.Builder().
//                    setDisplayName(userNameFromEmail(user.email!!)) // updating a profile
//                    .build()
//            )
//            Toast.makeText(this@LoginActivity,
//                "Registration OK", Toast.LENGTH_LONG).show()
//
//        }.addOnFailureListener{
//            Toast.makeText(this@LoginActivity,
//                "Register error ${it.message}", Toast.LENGTH_LONG).show()
//        }

    }

    fun loginClick(v: View){
        if (!isFormValid()) {
            return
        }
// SPOTIFY STUFF
//
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        val token = AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
        Log.d("THE TOKEN", token.toString())
//        // needs a callback method so that you know this connection and msg to Firebase was successful or not
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(
//            etEmail.text.toString(), etPassword.text.toString()
//        ).addOnSuccessListener {
//
//            // show main screen --> actually log the person in
           //startActivity(Intent(this@LoginActivity, FeedActivity::class.java))
//
//        }.addOnFailureListener{
//            Toast.makeText(this@LoginActivity,
//                "Login error ${it.message}", Toast.LENGTH_LONG).show()
//        }


    }

    private fun isFormValid(): Boolean {
        return when {
            etEmail.text.isEmpty() -> {
                etEmail.error = "This field can not be empty"
                false
            }
            etPassword.text.isEmpty() -> {
                etPassword.error = "This field can not be empty"
                false
            }
            else -> true
        }
    }

    private fun userNameFromEmail(email: String) = email.substringBefore("@")

    //SPOTIFY STUFF
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
                // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    Log.d("THE_TOKEN", response.accessToken)

                }

                // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                    Log.d("THE_TOKEN", response.error)
                }
            }// Handle successful response
            // Handle error response
            // Most likely auth flow was cancelled
            // Handle other cases
        }
    }

}

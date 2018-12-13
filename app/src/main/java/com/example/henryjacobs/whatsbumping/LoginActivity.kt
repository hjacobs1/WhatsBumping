package com.example.henryjacobs.whatsbumping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.henryjacobs.whatsbumping.data.Followers
import com.example.henryjacobs.whatsbumping.data.User
import com.example.henryjacobs.whatsbumping.data.UserResult
import com.example.henryjacobs.whatsbumping.network.UserAPI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.spotify.sdk.android.authentication.AuthenticationRequest
import kotlinx.android.synthetic.main.activity_login.*
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE
import com.squareup.okhttp.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class LoginActivity : AppCompatActivity() {
    //SPOTIFY STUFF
    //var api = SpotifyApi.Builder().set
    val REQUEST_CODE = 1337
    val REDIRECT_URI = "myapp://authresponse"
    val CLIENT_ID = "413f92986b554b8f91d2c0c407ca340b"

    val HOST_URL = "https://api.spotify.com/"
    var userAPI : UserAPI? = null
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initAPI()
    }

    fun initAPI(){
        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userAPI = retrofit.create(UserAPI::class.java)
    }

    fun loginClick(v: View){
        //startActivity(Intent(this@LoginActivity, FeedActivity::class.java).putExtra("name", "Ethan Hardacre"))
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        val token = AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
        Log.d("THE TOKEN", token.toString())

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
                    token = response.accessToken

                    var httpClient = OkHttpClient.Builder()
                    httpClient.addInterceptor { chain ->
                        var original = chain.request()
                        var request = original.newBuilder()
                            .addHeader("Accept","application/json")
                                .addHeader("Content-Type","application/json")
                                .addHeader("Authorization", "Bearer ${token}")
                                .method(original.method(),original.body())
                                .build()
                        chain.proceed(request)
                    }
                    val userCall = userAPI!!.getUserResults()
                    userCall.enqueue(object : Callback<UserResult> {

                        override fun onFailure(call: Call<UserResult>?, t: Throwable?) {
                            Log.d("Failure",t!!.message)
                            Toast.makeText(this@LoginActivity,"Failed to gather user data",Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<UserResult>?, response: Response<UserResult>?) {
                            val userResult = response?.body()
                            var name = userResult?.display_name.toString()
                            var email = userResult?.email.toString()
                            
//                            if(name == null){
//                                name = "Ethan Hardacre"
//                                email = "hardacre.ethan@gmail.com"
//                            }
                            registerUser(name,email)
                        }
                    })
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

    fun registerUser(name: String, email: String){
        var user = User(name,email)
        val userCollection = FirebaseFirestore.getInstance().collection("users")
        userCollection.add(user).addOnSuccessListener {
            Toast.makeText(this@LoginActivity, "Welcome, ${name}",Toast.LENGTH_LONG).show()
            var intent = Intent(this@LoginActivity,FeedActivity::class.java)
            intent.putExtra("email",email)
            intent.putExtra("name",name)
            startActivity(intent)
        }.addOnFailureListener {
            Toast.makeText(this@LoginActivity, "Sorry, there was a problem adding the new user",Toast.LENGTH_LONG).show()
        }

    }

}

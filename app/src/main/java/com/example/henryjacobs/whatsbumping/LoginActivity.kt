package com.example.henryjacobs.whatsbumping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun registerClick(v: View) {
        if (!isFormValid()) {
            return
        }
        // needs a callback method so that you know this connection and msg to Firebase was successful or not
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            etEmail.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {
            val user = it.user // firebase user
            user.updateProfile(
                UserProfileChangeRequest.Builder().
                    setDisplayName(userNameFromEmail(user.email!!)) // updating a profile
                    .build()
            )
            Toast.makeText(this@LoginActivity,
                "Registration OK", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(this@LoginActivity,
                "Register error ${it.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun loginClick(v: View){
        if (!isFormValid()) {
            return
        }
        // needs a callback method so that you know this connection and msg to Firebase was successful or not
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            etEmail.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {

            // show main screen --> actually log the person in
            startActivity(Intent(this@LoginActivity, FeedActivity::class.java))

        }.addOnFailureListener{
            Toast.makeText(this@LoginActivity,
                "Login error ${it.message}", Toast.LENGTH_LONG).show()
        }
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
}

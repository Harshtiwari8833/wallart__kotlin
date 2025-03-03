package com.maverickbits.wallart.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.maverickbits.wallart.MainActivity
import com.maverickbits.wallart.R

class SigningWithGoogleActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val GOOGLE_REQ_CODE = 123
    private val token="83148076889-v57in5ca1hrht4koci995kusun51v4ep.apps.googleusercontent.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signing_with_google)


        // Configure Google Sign-In with new OAuth Client ID
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("YOUR_NEW_CLIENT_ID.apps.googleusercontent.com") // Replace with actual OAuth Client ID
//            .requestIdToken(token) // Replace with actual OAuth Client ID
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInBtn = findViewById<Button>(R.id.button)
        signInBtn.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQ_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                handleSignInResult(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Sign-in failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSignInResult(account: GoogleSignInAccount?) {
        if (account != null) {
            val email = account.email
            val displayName = account.displayName

            val sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE).edit()
            sharedPreferences.putString("userEmail", email)
            sharedPreferences.putString("userName", displayName)
            sharedPreferences.putString("userImg", account.photoUrl.toString())
            sharedPreferences.putBoolean("isLoggedIn", true)
            sharedPreferences.apply()




            Toast.makeText(this, "Signed in as: $email", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Sign-in failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}

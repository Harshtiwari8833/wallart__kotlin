package com.maverickbits.wallart.Activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.SignInButton
import com.google.firebase.firestore.auth.User
import com.maverickbits.wallart.MainActivity
import com.maverickbits.wallart.R

class SigningWithGoogleActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val GOOGLE_REQ_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signing_with_google)
        val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        val siginbtn = findViewById<Button>(R.id.button)

        siginbtn.setOnClickListener {
            sigIn()
        }


    }

    fun sigIn(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQ_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_REQ_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(
                data!!
            )
            handleSignInResult(result!!)
        }
    }



    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val account = result.signInAccount
            val email = account!!.email



            val displayName = account.displayName
            val photoUrl = account.photoUrl.toString()

            val pref = getSharedPreferences("userName", MODE_PRIVATE)
            pref.getString("flag", "")
            val editor = pref.edit()
            editor.putString("flag", displayName.toString())
            editor.apply()

            val pref3 = getSharedPreferences("userImg", MODE_PRIVATE)
            val editor3 = pref3.edit()
            editor3.putString("img", account.photoUrl.toString())
            editor3.apply()



            val pref1 = getSharedPreferences("login" , MODE_PRIVATE)
            val login = pref1.getBoolean("flag1", false)
            var editor1 = pref1.edit()
            editor1.putBoolean("flag1",true)
            editor1.apply()


            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "signed in as: $email", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Sign-in failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.maverickbits.wallart.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.maverickbits.wallart.MainActivity
import com.maverickbits.wallart.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            val pref = getSharedPreferences("onboarding" , MODE_PRIVATE)
            val onboarding = pref.getBoolean("flag", false)

            val pref1 = getSharedPreferences("login" , MODE_PRIVATE)
            val login = pref1.getBoolean("flag1", false)
            if (false) {
                val mainIntent = Intent(this, SigningWithGoogleActivity::class.java)
                startActivity(mainIntent)
                finish()
            }else if(true){
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }else{
                val mainIntent = Intent(this, OnboardingActivity::class.java)
                startActivity(mainIntent)
                finish()
            }

        }, 1500)

    }
}
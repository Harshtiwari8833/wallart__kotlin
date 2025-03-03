package com.maverickbits.wallart.Activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.maverickbits.wallart.MainActivity
import com.maverickbits.wallart.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1500)
            navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen() {
        val onboarding = getSharedPreferences("onboarding", MODE_PRIVATE)
            .getBoolean("flag", false)

        val isLoggedIn = getSharedPreferences("userData", MODE_PRIVATE)
            .getBoolean("isLoggedIn", false)

        val nextActivity = when {
            isLoggedIn -> MainActivity::class.java
            onboarding -> SigningWithGoogleActivity::class.java
            else -> OnboardingActivity::class.java
        }

        startActivity(Intent(this, nextActivity))
        finish()
    }
}

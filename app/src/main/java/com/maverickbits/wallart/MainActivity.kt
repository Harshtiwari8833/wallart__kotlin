package com.maverickbits.wallart

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import androidx.fragment.app.Fragment
import com.maverickbits.wallart.Fragments.favourite_fragment
import com.maverickbits.wallart.Fragments.profile_fragment
import com.maverickbits.wallart.Fragments.wall_fragment
import com.maverickbits.wallart.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentManager = supportFragmentManager
    private lateinit var activeFragment: Fragment

    private lateinit var internetLayout : RelativeLayout
    private lateinit var noInternetLayout : RelativeLayout
    private lateinit var btn_tryAgain : Button

    private val wallpaper = wall_fragment()
    private val category = category_fragment()
    private val favourite = favourite_fragment()
    private val profile = profile_fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.collapsingToolBar)

        internetLayout = findViewById(R.id.internetLayout)
        noInternetLayout = findViewById(R.id.noInternetLayout)
        btn_tryAgain = findViewById(R.id.btn_tryAgain)



        setSupportActionBar(toolbar)

        // Set the initial fragment
        activeFragment = wallpaper
        fragmentManager.beginTransaction().add(R.id.frame, profile, "profile").hide(profile).commit()
        fragmentManager.beginTransaction().add(R.id.frame, favourite, "favourite").hide(favourite).commit()
        fragmentManager.beginTransaction().add(R.id.frame, category, "category").hide(category).commit()
        fragmentManager.beginTransaction().add(R.id.frame, wallpaper, "wallpaper").commit()

        binding.btmNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.wall -> showFragment(wallpaper)
                R.id.cat -> showFragment(category)
                R.id.fav -> showFragment(favourite)
                R.id.profile -> showFragment(profile)
            }
            true
        }


        drawLayout()
        btn_tryAgain.setOnClickListener{
            drawLayout()
        }
    }

    private fun isNetworkAvailable() : Boolean{
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))
    }

    private fun drawLayout(){
        if(isNetworkAvailable()){
            internetLayout.visibility = View.VISIBLE
            noInternetLayout.visibility = GONE
        }

        else{
            noInternetLayout.visibility = View.VISIBLE
            internetLayout.visibility = GONE
        }
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }


}

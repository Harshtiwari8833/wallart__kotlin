package com.maverickbits.wallart

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.maverickbits.wallart.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wallpaper = wall_fragment()
        val category = category_fragment()
        val favourite = favourite_fragment()
        val profile = profile_fragment()

        loadfragment(wallpaper)

        binding.btmNav.setOnItemSelectedListener {
            when(it.itemId) {

                R.id.wall -> loadfragment(wallpaper)
                R.id.cat -> loadfragment(category)
                R.id.fav -> loadfragment(favourite)
                R.id.profile -> loadfragment(profile)
            }
            true
        }
    }

    private fun loadfragment( fragment:Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }
}
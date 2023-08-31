package com.maverickbits.wallart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    private val wallpaper = wall_fragment()
    private val category = category_fragment()
    private val favourite = favourite_fragment()
    private val profile = profile_fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.collapsingToolBar)

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
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }
}

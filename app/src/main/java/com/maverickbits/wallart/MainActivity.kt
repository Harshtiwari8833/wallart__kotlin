package com.maverickbits.wallart

import android.os.Bundle
import android.util.Log
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
    var resourceId: Int = R.id.wall
    val wallpaper = wall_fragment()
    val category = category_fragment()
    val favourite = favourite_fragment()
    val profile = profile_fragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.collapsingToolBar)

        setSupportActionBar(toolbar)


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

    override fun onRestoreInstanceState(inState: Bundle) {
        Log.d("hi", "onRestoreInstanceState: ")
        // Restore the saved variables.

        // Restore the saved variables.
        resourceId = inState.getInt("fragmentType", R.id.wall)
        if (resourceId == R.id.wall) {
            loadfragment(wallpaper)
        } else if (resourceId == R.id.cat) {
            loadfragment(category)
        } else if (resourceId == R.id.fav) {
            loadfragment(favourite)
        } else {
            loadfragment(profile)
        }
        binding.btmNav.setSelectedItemId(resourceId)
    }

    override fun onSaveInstanceState(outState: Bundle) {

        // Save the variables.
        outState.putInt("fragmentType", resourceId)
        super.onSaveInstanceState(outState)
    }
}
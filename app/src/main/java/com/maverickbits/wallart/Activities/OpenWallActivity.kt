package com.maverickbits.wallart.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.maverickbits.wallart.Adapter.OpenWallAdapter
import com.maverickbits.wallart.R
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.Repositery.openWallRepotwo
import com.maverickbits.wallart.Repositery.retorfit
import com.maverickbits.wallart.ViewModel.OpenWallViewModel
import com.maverickbits.wallart.ViewModel.OpenWallViewModelFactory
import com.maverickbits.wallart.databinding.ActivityOpenWallBinding

class OpenWallActivity : AppCompatActivity() {
    private lateinit var viewpager: ViewPager2
    private lateinit var viewModel: OpenWallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        super.onCreate(savedInstanceState)
        val binding = ActivityOpenWallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("lifecycle","enter in oncreate")
        viewpager = findViewById(R.id.viewPager)

        val apiService = retorfit.apiService
        val repository = WallRepo(apiService)

        val pref =getSharedPreferences("animation_viewpager", AppCompatActivity.MODE_PRIVATE)
        val check = pref.getBoolean("flag1", false)
        viewModel = ViewModelProvider(this, OpenWallViewModelFactory(repository))
            .get(OpenWallViewModel::class.java)
        Log.d("lifecycle","viewmodel created")
//        viewModel.wallpapers.observe(this) { wallpapers ->
//            if (wallpapers != null && wallpapers.isNotEmpty()) {
//                val adapter = OpenWallAdapter(this, wallpapers,this){
//                    if (check){
//                        binding.viewpagerLoader.visibility = View.GONE
//                        val editor = pref.edit()
//                        editor.putBoolean("flag1", false)
//                        editor.apply()
//                    }
//                }
//                binding.viewPager.adapter = adapter
//                binding.viewPager.setCurrentItem(pos, false)
//
//            }
//        }

        val pos = intent.extras!!.getInt("wall_pos")
        Log.d("lifecycle","positioin get: $pos")
        //... pagination
        val adapter = OpenWallAdapter(this,this){
            Log.d("lifecycle","inside adapter calling")
        }

        binding.viewPager.adapter = adapter


        viewModel.list.observe(this, Observer {

            Log.d("lifecycle","inside observer")
            adapter.submitData(lifecycle, it)
            Log.d("lifecycle","called submitData method inside observer")
            viewpager.setCurrentItem(6, false)
        })
        Log.d("lifecycle","in the end of oncreate")
    }

    override fun onResume() {
        super.onResume()
  Log.d("lifecycle","resume")

        Toast.makeText(this,"resume", Toast.LENGTH_SHORT).show()

    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onstart")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("lifecycle","onRestart")
    }
}
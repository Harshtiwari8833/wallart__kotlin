package com.maverickbits.wallart.Activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.maverickbits.wallart.Adapter.OpenWallAdapter
import com.maverickbits.wallart.R
import com.maverickbits.wallart.Repositery.openWallRepotwo
import com.maverickbits.wallart.Repositery.retorfit
import com.maverickbits.wallart.ViewModel.OpenWallViewModel
import com.maverickbits.wallart.ViewModel.OpenWallViewModelFactory
import com.maverickbits.wallart.databinding.ActivityOpenWallBinding

class OpenWallActivity : AppCompatActivity() {
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

        val pos = intent.extras!!.getInt("wall_pos")

        val apiService = retorfit.apiService
        val repository = openWallRepotwo(apiService)

        val pref =getSharedPreferences("animation_viewpager", AppCompatActivity.MODE_PRIVATE)
        val check = pref.getBoolean("flag1", false)
        viewModel = ViewModelProvider(this, OpenWallViewModelFactory(repository))
            .get(OpenWallViewModel::class.java)

        viewModel.wallpapers.observe(this) { wallpapers ->
            if (wallpapers != null && wallpapers.isNotEmpty()) {
                val adapter = OpenWallAdapter(this, wallpapers,this){
                    if (check){
                        binding.viewpagerLoader.visibility = View.GONE
                        val editor = pref.edit()
                        editor.putBoolean("flag1", false)
                        editor.apply()
                    }
                }
                binding.viewPager.adapter = adapter
                binding.viewPager.setCurrentItem(pos, false)

            }
        }


    }
}
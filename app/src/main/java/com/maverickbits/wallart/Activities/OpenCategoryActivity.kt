package com.maverickbits.wallart.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Adapter.OpenCatAdapter
import com.maverickbits.wallart.Adapter.OpenWallAdapter
import com.maverickbits.wallart.Models.CatWallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.Repositery.openWallRepotwo
import com.maverickbits.wallart.Repositery.retorfit
import com.maverickbits.wallart.ViewModel.OpenCatVeiwModelFactory
import com.maverickbits.wallart.ViewModel.OpenCatViewModel
import com.maverickbits.wallart.ViewModel.OpenWallViewModel
import com.maverickbits.wallart.ViewModel.OpenWallViewModelFactory
import com.maverickbits.wallart.databinding.ActivityOpenCategoryBinding
import com.maverickbits.wallart.databinding.ActivityOpenWallBinding

class OpenCategoryActivity : AppCompatActivity() {
    private lateinit var viewModel: OpenCatViewModel
    private lateinit var category :String
    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pos = intent.extras!!.getInt("wall_pos")
        val apiService = retorfit.apiService
        val repository = openWallRepotwo(apiService)
        val pref  = getSharedPreferences("Category", MODE_PRIVATE )
        category = pref.getString("value", "").toString()
        viewModel = ViewModelProvider(this, OpenCatVeiwModelFactory(repository,category)).get(OpenCatViewModel::class.java)
        val pref1 =getSharedPreferences("animation", AppCompatActivity.MODE_PRIVATE)
        val check = pref1.getBoolean("flag1", false)
        viewModel.wallpapers.observe(this) { wallpapers ->
            if (wallpapers != null && wallpapers.isNotEmpty()) {
                val adapter = OpenCatAdapter(this,wallpapers, this){
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
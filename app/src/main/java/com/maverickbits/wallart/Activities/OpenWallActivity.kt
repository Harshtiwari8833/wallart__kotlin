package com.maverickbits.wallart.Activities

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Adapter.OpenWallAdapter
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.ApiUtilities
import com.maverickbits.wallart.Repositery.OpenWallRepo
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.ViewModel.OpenWallViewModel
import com.maverickbits.wallart.ViewModel.OpenWallViewModelFactory
import com.maverickbits.wallart.ViewModel.WallViewModel
import com.maverickbits.wallart.ViewModel.WallViewModelFactory
import com.maverickbits.wallart.databinding.ActivityOpenWallBinding

class OpenWallActivity : AppCompatActivity() {
    lateinit var openWallViewModel : OpenWallViewModel
    private lateinit var adapter: OpenWallAdapter
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

        adapter = OpenWallAdapter(this, this)
        binding.viewPager.adapter = adapter

        val apiInstance = ApiUtilities.getInstance().create(ApiInterface::class.java)
        val repository = OpenWallRepo(apiInstance)

       openWallViewModel = ViewModelProvider(
            this,
            OpenWallViewModelFactory(repository)
          ).get(OpenWallViewModel::class.java)

        openWallViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })

    }
}
package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.Models.CatWallpaper
import com.maverickbits.wallart.Repositery.openWallRepotwo

class OpenCatViewModel (private val repository: openWallRepotwo, private  val category:String): ViewModel() {
    val wallpapers: LiveData<List<CatWallpaper>> = repository.getAllCatWallpapers(category)
}
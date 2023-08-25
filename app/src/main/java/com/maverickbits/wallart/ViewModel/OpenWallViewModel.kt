package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.Repositery.OpenWallRepo
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.Repositery.openWallRepotwo

class OpenWallViewModel(private val repository: openWallRepotwo) : ViewModel() {

//    val list = repo.getOpenWalls().cachedIn(viewModelScope)
//private val service: ApiInterface = retrofit.create(ApiInterface::class.java)

//    private val _wallpapers = MutableLiveData<List<Wallpaper>>()
//    val wallpapers: LiveData<List<Wallpaper>> = _wallpapers

    // Function to fetch wallpapers for a given page
//    private suspend fun fetchWallpapersForPage(page: Int): List<Wallpaper> {
//        val response = service.getAllWallpapers(page)
//        return if (response.isSuccessful) {
//            val wallpaperResponse = response.body()
//            wallpaperResponse?.wallpapers ?: emptyList()
//        } else {
//            emptyList()
//        }
//    }

    // Function to fetch all wallpapers across all pages
//    suspend fun fetchAllWallpapers() {
//        val allWallpapers = mutableListOf<Wallpaper>()
//
//        val totalPages = 3
//
//            for (page in 1..totalPages) {
//                val wallpapersForPage = fetchWallpapersForPage(page)
//                allWallpapers.addAll(wallpapersForPage)
//            }

//        _wallpapers.postValue(allWallpapers)
//    }

    val wallpapers: LiveData<List<Wallpaper>> = repository.getAllWallpapers()
}
package com.maverickbits.wallart.Repositery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.Models.CatWallpaper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class openWallRepotwo(private val apiService: ApiInterface) {

    fun getAllWallpapers(): LiveData<List<Wallpaper>> {
        val wallpapersLiveData = MutableLiveData<List<Wallpaper>>()

        GlobalScope.launch {
            val allWallpapers = mutableListOf<Wallpaper>()
            var currentPage = 1
            var totalPages = 1

            while (currentPage <= totalPages) {
                val response = apiService.getWallpapers(currentPage)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let {
                        allWallpapers.addAll(it.wallpapers)
                        totalPages = it.totalPages
                    }
                }
                currentPage++
            }

            wallpapersLiveData.postValue(allWallpapers)
        }

        return wallpapersLiveData
    }
    fun getAllCatWallpapers(category:String): LiveData<List<CatWallpaper>>{
        val wallpapersLiveData = MutableLiveData<List<CatWallpaper>>()
        GlobalScope.launch {
            val allWallpapers = mutableListOf<CatWallpaper>()
            var currentPage = 1
            var totalPages = 1

            while (currentPage <= totalPages) {
                val response = apiService.getCat(category,currentPage)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let {
                        allWallpapers.addAll(it.wallpapers)
                        totalPages = it.totalPages
                    }
                }
                currentPage++
            }

            wallpapersLiveData.postValue(allWallpapers)
        }

        return wallpapersLiveData
    }

}
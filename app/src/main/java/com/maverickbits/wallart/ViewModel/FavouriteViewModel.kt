//package com.maverickbits.wallart.ViewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.maverickbits.wallart.Models.FavModel
//import com.maverickbits.wallart.Models.WallModel
//import com.maverickbits.wallart.Repositery.WallRepo
//
//class FavouriteViewModel(private val repo: WallRepo): ViewModel() {
//
//    fun getfav(email:String): LiveData<List<FavModel>>{
//        return repo.getFavourite(email)
//    }
//}
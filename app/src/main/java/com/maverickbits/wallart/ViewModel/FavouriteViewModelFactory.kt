//package com.maverickbits.wallart.ViewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.maverickbits.wallart.Repositery.WallRepo
//
//class FavouriteViewModelFactory(private val wallRepo: WallRepo):ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>):T{
//        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
//            return FavouriteViewModel(WallRepo()) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.RoomDatabase.FavRepository

class FavFactoryViewModel(private val favRepository: FavRepository):ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavViewModel(favRepository) as T


    }
}
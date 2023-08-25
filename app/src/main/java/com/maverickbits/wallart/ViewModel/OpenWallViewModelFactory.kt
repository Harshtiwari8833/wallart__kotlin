package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Repositery.OpenWallRepo
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.Repositery.openWallRepotwo

class OpenWallViewModelFactory(private val wallRepo: openWallRepotwo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OpenWallViewModel::class.java)) {
            return OpenWallViewModel(wallRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.Repositery.WallRepo

class WallViewModelFactory(private val wallRepo: WallRepo) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WallViewModel::class.java)) {
            return WallViewModel(WallRepo()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
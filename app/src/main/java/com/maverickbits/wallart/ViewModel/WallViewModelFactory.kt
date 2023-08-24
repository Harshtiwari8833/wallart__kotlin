package com.maverickbits.wallart.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Repositery.WallRepo

class WallViewModelFactory( private val wallRepo: WallRepo) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WallViewModel::class.java)) {
            return WallViewModel( wallRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
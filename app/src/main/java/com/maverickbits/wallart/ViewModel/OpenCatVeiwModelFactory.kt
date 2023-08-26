package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Repositery.openWallRepotwo

class OpenCatVeiwModelFactory(private val wallRepo: openWallRepotwo, private  val category : String): ViewModelProvider.Factory {

   override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OpenCatViewModel::class.java)) {
            return OpenCatViewModel(wallRepo, category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
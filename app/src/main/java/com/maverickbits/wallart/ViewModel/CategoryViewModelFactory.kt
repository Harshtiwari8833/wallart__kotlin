package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Repositery.WallRepo

class CategoryViewModelFactory(private val wallRepo: WallRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(WallRepo()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
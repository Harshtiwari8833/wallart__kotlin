package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Repositery.WallRepo

class CategoryViewModelFactory(private val wallRepo: WallRepo , private val category: String) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(wallRepo,category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
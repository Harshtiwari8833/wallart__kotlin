package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.Repositery.WallRepo

class CategoryViewModel( private val repo : WallRepo) : ViewModel() {

    fun getcat( key : String): LiveData<List<WallModel>> {
        return repo.getCategory(key)
    }
}
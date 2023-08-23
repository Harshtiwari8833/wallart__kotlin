package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverickbits.wallart.Api.WallModel
import com.maverickbits.wallart.Repositery.WallRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WallViewModel( private val repo : WallRepo) : ViewModel() {

    init {

        viewModelScope.launch(Dispatchers.IO){
            repo.getAllWall()

        }
    }

    val allWall :LiveData<WallModel>
    get() = repo.allWall
}
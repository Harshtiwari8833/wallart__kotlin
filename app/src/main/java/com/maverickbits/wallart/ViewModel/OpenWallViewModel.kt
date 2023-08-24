package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maverickbits.wallart.Repositery.OpenWallRepo
import com.maverickbits.wallart.Repositery.WallRepo

class OpenWallViewModel(val repo : OpenWallRepo) : ViewModel() {

    val list = repo.getOpenWalls().cachedIn(viewModelScope)
}
package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.Repositery.WallRepo


class WallViewModel(val repo: WallRepo) : ViewModel() {
//    private val repo = WallRepo()

    private val _wallpapers = MutableLiveData<List<WallModel>>()
    val wallpapers: LiveData<List<WallModel>> = _wallpapers

    fun loadInitialData(limit: Long) {
        repo.getInitialData(limit) { wallpapers ->
            _wallpapers.value = wallpapers
        }
    }

    fun loadMoreData(limit: Long) {
        repo.loadMoreData(limit) { wallpapers ->
            _wallpapers.value = (_wallpapers.value ?: emptyList()) + wallpapers
        }
    }
}
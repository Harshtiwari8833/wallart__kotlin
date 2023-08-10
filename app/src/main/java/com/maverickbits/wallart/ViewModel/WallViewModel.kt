package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.Repositery.WallRepo


class WallViewModel( private val repo : WallRepo) : ViewModel() {
     fun getwallpaper(): LiveData<List<WallModel>>{

        return repo.getWall()
     }
}
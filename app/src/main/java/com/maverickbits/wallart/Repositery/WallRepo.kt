package com.maverickbits.wallart.Repositery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.WallModel

class WallRepo(private val apiInterface :ApiInterface){

    private val allWallLiveData  = MutableLiveData<WallModel>()
    val allWall :LiveData<WallModel> get() = allWallLiveData

    suspend fun getAllWall(){
        val result = apiInterface.getAllWall()
        if(result?.body()!=null) {
            allWallLiveData.postValue(result.body())

        }
    }

}


package com.maverickbits.wallart.Repositery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.WallModel
import com.maverickbits.wallart.pagging.WallCatPaggingSource
import com.maverickbits.wallart.pagging.WallPaggingSource
import javax.inject.Inject

class WallRepo @Inject constructor(private val apiInterface :ApiInterface){



//    suspend fun getAllWall(){
//        val result = apiInterface.getAllWall()
//        if(result?.body()!=null) {
//            allWallLiveData.postValue(result.body())
//
//        }
//    }

    fun getWalls()= Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {WallPaggingSource(apiInterface)}
    ).liveData


    fun getCatWalls( category : String)= Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {WallCatPaggingSource(apiInterface,category)}
    ).liveData

}


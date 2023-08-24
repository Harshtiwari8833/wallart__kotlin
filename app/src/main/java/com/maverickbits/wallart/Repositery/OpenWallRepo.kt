package com.maverickbits.wallart.Repositery

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.pagging.WallPaggingSource
import javax.inject.Inject

class OpenWallRepo  @Inject constructor(private val apiInterface : ApiInterface){
    fun getOpenWalls()= Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = { WallPaggingSource(apiInterface) }
    ).liveData

}
package com.maverickbits.wallart.Repositery

import android.security.identity.AccessControlProfileId
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.pagging.WallPaggingSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class OpenWallRepo  @Inject constructor(private val apiInterface : ApiInterface){
//    fun getOpenWalls()= Pager(
//        config = PagingConfig(pageSize = 10, maxSize = 100),
//        pagingSourceFactory = { WallPaggingSource(apiInterface) }
//    ).liveData

    val retrofit = Retrofit.Builder()
        .baseUrl("https://magnificent-deer-cap.cyclic.cloud/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiInterface::class.java)

}
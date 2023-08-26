package com.maverickbits.wallart.Api

import com.maverickbits.wallart.Models.CatModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/wall")
    suspend fun getAllWall(@Query("page") page: Int): WallModel

    @GET("/wall")
    suspend fun getWallpapers(@Query("page") page: Int): Response<WallModel>

    @GET("/wall/cat")
    suspend fun getCatWallpaper(@Query("cat") cat: String, @Query("page") page: Int): CatModel
}

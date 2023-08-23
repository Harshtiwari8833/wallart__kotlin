package com.maverickbits.wallart.Api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/wall")
    suspend fun getAllWall(@Query("page") page: Int): WallModel
}
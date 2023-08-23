package com.maverickbits.wallart.Api

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/wall")
    suspend fun getAllWall(): Response<WallModel>
}
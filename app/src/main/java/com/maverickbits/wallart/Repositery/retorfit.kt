package com.maverickbits.wallart.Repositery

import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retorfit {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiInterface = retrofit.create(ApiInterface::class.java)
}
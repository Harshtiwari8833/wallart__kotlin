package com.maverickbits.wallart.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

//    private const val BASE_URL="https://busy-tan-piranha-robe.cyclic.app/"


    private const val BASE_URL="https://wallart-5ccj.onrender.com/"
    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
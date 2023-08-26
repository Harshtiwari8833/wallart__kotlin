package com.maverickbits.wallart.di

import android.util.Log
import com.google.gson.Gson
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent ::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Singleton
    @Provides
    fun getWallApi(retrofit: Retrofit):ApiInterface{
        return retrofit.create(ApiInterface ::class.java)
    }
}
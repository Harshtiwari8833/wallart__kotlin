package com.maverickbits.wallart.RoomDatabase

import androidx.lifecycle.LiveData

class FavRepository(private val favDao: FavDao) {

    val getAllFav :LiveData<List<FavModel>> = favDao.getAllFav()

    suspend fun insert(favModel: FavModel)
    {
        favDao.insertFav(favModel)
    }


    suspend fun delete(favModel: FavModel){

        favDao.deleteFav(favModel)
    }
}
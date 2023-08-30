package com.maverickbits.wallart.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(favModel: FavModel)

    @Delete
    suspend fun deleteFav(favModel: FavModel)


    @Query("SELECT * FROM FavTable")
    fun getAllFav():LiveData<List<FavModel>>







}
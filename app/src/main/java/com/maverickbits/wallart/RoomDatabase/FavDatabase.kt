package com.maverickbits.wallart.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavModel::class], version = 1, exportSchema = false)
abstract class FavDatabase:RoomDatabase() {

    abstract fun getFavDao():FavDao


    companion object {
        private var INSTANCE: FavDatabase? = null
        fun getDatabase(context: Context): FavDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, FavDatabase::class.java, "FavDB.db").build()
                }
            }
            return INSTANCE!!
        }
    }
}
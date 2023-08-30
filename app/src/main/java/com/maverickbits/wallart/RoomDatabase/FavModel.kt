package com.maverickbits.wallart.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavTable")
data class FavModel(
    @PrimaryKey
    val WallId:String,
    @ColumnInfo("ImageUrl")val imgurl: String?=null
):java.io.Serializable

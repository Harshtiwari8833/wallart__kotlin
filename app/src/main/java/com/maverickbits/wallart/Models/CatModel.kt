package com.maverickbits.wallart.Models

data class CatModel(
    val category: String,
    val currentPage: Int,
    val timestamp: String,
    val totalPages: Int,
    val wallpapers: List<CatWallpaper>
)
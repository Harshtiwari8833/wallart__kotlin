package com.maverickbits.wallart.Api

data class WallModel(
    val page: Int,
    val totalPages: Int,
    val wallpapers: List<Wallpaper>
)
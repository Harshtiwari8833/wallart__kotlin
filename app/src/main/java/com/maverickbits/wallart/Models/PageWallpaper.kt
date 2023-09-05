package com.maverickbits.wallart.Models

import com.maverickbits.wallart.Api.Wallpaper

data class PageWallpaper(
    val wallpaper: Wallpaper,
    val page: Int
)

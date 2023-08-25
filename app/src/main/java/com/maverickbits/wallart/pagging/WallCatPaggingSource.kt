package com.maverickbits.wallart.pagging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.Wallpaper

class WallCatPaggingSource(val apiInterface: ApiInterface) : PagingSource<Int, Wallpaper>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        return try{
            val position = params.key ?: 1
            val response = apiInterface.getCatWallpaper("nature",position)
            LoadResult.Page(
                data = response.wallpapers,
                prevKey = if (position == 1)null else position -1,
                nextKey = if (position == response.totalPages) null else position +1
            )
        }catch(e:java.lang.Exception){
            LoadResult.Error(e)
        }
    }
    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus ( 1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus ( 1)
        }
    }

}
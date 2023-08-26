package com.maverickbits.wallart.pagging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Models.CatModel
import com.maverickbits.wallart.Models.CatWallpaper

class WallCatPaggingSource(val apiInterface: ApiInterface, val category: String) : PagingSource<Int, CatWallpaper>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatWallpaper> {
        return try{
            val position = params.key ?: 1
            val response = apiInterface.getCatWallpaper(category,position)

//            Log.d("test12",response.toString())
            LoadResult.Page(
                data = response.wallpapers,
                prevKey = if (position == 1)null else position -1,
                nextKey = if (position == response.totalPages) null else position +1
            )
        }catch(e:java.lang.Exception){
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, CatWallpaper>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus ( 1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus ( 1)
        }
    }

}
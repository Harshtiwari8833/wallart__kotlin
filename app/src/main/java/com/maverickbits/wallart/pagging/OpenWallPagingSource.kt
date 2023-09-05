package com.maverickbits.wallart.pagging


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.Models.PageWallpaper
private const val TAG = "openwallpaginsource"
class OpenWallPagingSource(val apiInterface: ApiInterface): PagingSource<Int, Wallpaper>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        return try{
            Log.d(TAG, "load: ${params.key}")
            val position = params.key ?: 1
            Log.d(TAG, "loading: $position")
            val response = apiInterface.getWallpapers(position)
            LoadResult.Page(
                data = response.wallpapers,
                prevKey = if (position == 1)null else position -1,
                nextKey = if (position == response.totalPages) null else position +1
            )
        }catch(e:java.lang.Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus ( 1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus ( 1)
        }
    }

}
package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maverickbits.wallart.Repositery.WallRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class CategoryViewModel @Inject constructor(private val repo : WallRepo,val category:String) : ViewModel() {
      val list  = repo.getCatWalls(category).cachedIn(viewModelScope)
//      val list: LiveData<PagingData<CatModel>> = repo.getCatWalls().asLiveData().cachedIn(viewModelScope)
}
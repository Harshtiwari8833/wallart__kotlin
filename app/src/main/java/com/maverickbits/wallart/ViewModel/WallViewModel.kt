package com.maverickbits.wallart.ViewModel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.maverickbits.wallart.Api.WallModel
import com.maverickbits.wallart.Repositery.WallRepo
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(private val repo : WallRepo) : ViewModel() {



    val list = repo.getWalls().cachedIn(viewModelScope)




}
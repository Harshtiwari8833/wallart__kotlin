package com.maverickbits.wallart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maverickbits.wallart.RoomDatabase.FavModel
import com.maverickbits.wallart.RoomDatabase.FavRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel(private val favRepository: FavRepository):ViewModel() {


    fun insert(favModel: FavModel){
        CoroutineScope(Dispatchers.IO).launch{

            favRepository.insert(favModel)
        }
    }

    fun delete(favModel: FavModel){
        CoroutineScope(Dispatchers.IO).launch {
            favRepository.delete(favModel)
        }

    }

val list: LiveData<List<FavModel>> = favRepository.getAllFav

}
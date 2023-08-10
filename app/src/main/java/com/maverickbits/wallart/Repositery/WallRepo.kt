package com.maverickbits.wallart.Repositery

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.maverickbits.wallart.Models.WallModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class WallRepo {
   private val firestore = FirebaseFirestore.getInstance()
  private  val WallCollection = firestore.collection("Wallpaper")
    val usersList = mutableListOf < WallModel > ()

     fun getWall(): LiveData<List<WallModel>> {

        val list = MutableLiveData < List < WallModel >> ()

        GlobalScope.launch(Dispatchers.IO) {
            WallCollection.get().await().also {
                for (doc in it.documents)
                {
                    val data = doc.toObject(WallModel::class.java)
                    Log.d("url", data.toString())
                    usersList.add(data!!)
                }

            }

            withContext(Dispatchers.Main){
                list.value = usersList

            }

        }

         return list
    }
}
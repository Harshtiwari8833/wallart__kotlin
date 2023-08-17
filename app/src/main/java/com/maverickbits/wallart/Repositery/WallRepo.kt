package com.maverickbits.wallart.Repositery

import android.annotation.SuppressLint
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.maverickbits.wallart.Models.FavModel
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

                    usersList.add(data!!)
                }

            }

            withContext(Dispatchers.Main){
                list.value = usersList

            }

        }

         return list
    }
    val catList = mutableListOf < WallModel > ()
    @SuppressLint("SuspiciousIndentation")
    fun getCategory(category: String): LiveData<List<WallModel>>{

        val list = MutableLiveData < List < WallModel >> ()
          GlobalScope.launch(Dispatchers.IO){
              WallCollection.whereEqualTo("cat",category).get().await()
                .also {
                    for (doc in it.documents) {
                        val data = doc.toObject(WallModel::class.java)
                        catList.add(data!!)
                    }
                }

            withContext(Dispatchers.Main)
            {
                list.value = catList

            }
        }

         return list
    }
//    val FavList = mutableListOf < FavModel > ()
//    fun getFavourite(email: String):LiveData<List<FavModel>>{
//        val list = MutableLiveData < List < FavModel >> ()
//
//        GlobalScope.launch ( Dispatchers.IO){
//           val favCollection = firestore.collection(email)
//            favCollection.get().await().also {
//                for (doc in it.documents)
//                {
//                    val data = doc.toObject(FavModel::class.java)
//                    Log.d("url", data.toString())
//                    FavList.add(data!!)
//                }
//            }
//            withContext(Dispatchers.Main)
//            {
//                list.value = FavList
//            }
//        }
//        return list
//    }


    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users").child("harshtiwari8833").child("favouraite")

    fun getFavWallpapers(callback: (List<FavModel>) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val wallpapers = mutableListOf<FavModel>()
                for (childSnapshot in snapshot.children) {
                    val url = childSnapshot.child("url").getValue(String::class.java)
                    url?.let { wallpapers.add(FavModel(url)) }
                }
                callback(wallpapers)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}


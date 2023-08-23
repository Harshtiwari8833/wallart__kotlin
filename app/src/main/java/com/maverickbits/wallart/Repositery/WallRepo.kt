package com.maverickbits.wallart.Repositery

import android.annotation.SuppressLint
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentSnapshot
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

    private var lastVisibleDocument: DocumentSnapshot? = null

    val usersList = mutableListOf < WallModel > ()

//    fun getPaginatedWall(): MutableLiveData<List<WallModel>> {
//        val paginatedList = MutableLiveData<List<WallModel>>()
//
//        loadMoreData(paginatedList)
//
//        return paginatedList
//    }
fun getInitialData(limit: Long, callback: (List<WallModel>) -> Unit) {
    WallCollection
        .orderBy("key")
        .limit(limit)
        .get()
        .addOnSuccessListener { querySnapshot ->
            val wallpapers = querySnapshot.documents.map { it.toObject(WallModel::class.java)!! }
            lastVisibleDocument = querySnapshot.documents.lastOrNull()
            callback(wallpapers)
        }
}

    fun loadMoreData(limit: Long, callback: (List<WallModel>) -> Unit) {
        lastVisibleDocument?.let { lastDocument ->
           WallCollection
                .orderBy("key")
                .startAfter(lastDocument)
                .limit(limit)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val wallpapers = querySnapshot.documents.map { it.toObject(WallModel::class.java)!! }
                    lastVisibleDocument = querySnapshot.documents.lastOrNull()
                    callback(wallpapers)
                }
        }
    }

//    fun loadMoreData(paginatedList: MutableLiveData<List<WallModel>>) {
//
//        GlobalScope.launch(Dispatchers.IO) {
//            val query = if (lastVisibleDocument == null) {
//
//                WallCollection.orderBy("key")
//                    .limit(itemsPerPage.toLong())
//
//            } else {
//
//                WallCollection.orderBy("key")
//                    .startAfter(lastVisibleDocument)
//                    .limit(itemsPerPage.toLong())
//            }
//
//            Log.d("wall4", lastVisibleDocument.toString())
//            query.get().await().also { documents ->
//                val newDataList = mutableListOf<WallModel>()
//
//                for (doc in documents) {
//
//                    val data = doc.toObject(WallModel::class.java)
//                    newDataList.add(data)
//                    Log.d("wall2", data.toString())
//
//                }
//
//
//                val currentData = paginatedList.value ?: emptyList()
//                val updatedData = currentData + newDataList
//
//                lastVisibleDocument = documents.lastOrNull()
//                Log.d("key", lastVisibleDocument.toString())
//                withContext(Dispatchers.Main) {
//                    paginatedList.value = updatedData
//                }
//            }
//        }}


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




    fun getFavWallpapers(email:String, callback: (List<FavModel>) -> Unit) {
         val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users").child(email).child("favouraite")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val wallpapers = mutableListOf<FavModel>()
                wallpapers.clear()
                for (childSnapshot in snapshot.children) {
                    val id = childSnapshot.child("id").getValue(String::class.java)
                    val url = childSnapshot.child("url").getValue(String::class.java)
                    if (id != null && url != null) {
                        wallpapers.add(FavModel(url, id))
                    }
                    }

                callback(wallpapers)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}


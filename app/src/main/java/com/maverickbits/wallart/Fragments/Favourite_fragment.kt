package com.maverickbits.wallart.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.maverickbits.wallart.Adapter.FavAdapter
import com.maverickbits.wallart.Models.FavModel
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.databinding.FragmentFavouriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class favourite_fragment : Fragment() {

    private lateinit var adapter: FavAdapter
    private val favWallpapersList = mutableListOf<FavModel>()
    private val wallpaperRepository = WallRepo()
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var childEventListener: ChildEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)


        val pref9 =
            requireContext().getSharedPreferences("userEmail", AppCompatActivity.MODE_PRIVATE)
        val email = pref9.getString("flag", "")
        var index: Int = email!!.indexOf('@')
        val parseEmail = email.substring(0, index)


        adapter = FavAdapter()

        binding.recycleFav.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycleFav.adapter = adapter
        binding.recycleFav.setNestedScrollingEnabled(true)

        wallpaperRepository.getFavWallpapers { wallpapers ->
            adapter.submitList(wallpapers)



        }
        val database: DatabaseReference =
            FirebaseDatabase.getInstance().reference.child("users").child("harshtiwari8833")
                .child("favouraite")
        childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                wallpaperRepository.getFavWallpapers { wallpapers ->
                    adapter.submitList(wallpapers)
                }


                }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // This method is called when a child changes position in the list
                // Handle the change in position
            }

            override fun onCancelled(error: DatabaseError) {
                // This method is called if the listener encounters an error
                // Handle the error
            }
        }

        // Add the child event listener to the database reference
        database.addChildEventListener(childEventListener)


        return binding.root
    }
    fun updateListAfterItemRemoval(wallpaperId: String) {
        Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
        val updatedList = adapter.currentList.toMutableList()
        val itemToRemove = updatedList.find { it.id == wallpaperId }
        if (itemToRemove != null) {
            updatedList.remove(itemToRemove)
            adapter.submitList(updatedList)
        }
    }

    }

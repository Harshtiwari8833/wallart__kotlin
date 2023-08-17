package com.maverickbits.wallart.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val wallpaperRepository = WallRepo()
    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)

        val pref9 = requireContext().getSharedPreferences("userEmail", AppCompatActivity.MODE_PRIVATE)
        val email = pref9.getString("flag","")
        var index: Int = email!!.indexOf('@')
        val parseEmail = email.substring(0, index)

        adapter = FavAdapter()
        binding.recycleFav.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycleFav.adapter = adapter
        binding.recycleFav.setNestedScrollingEnabled(true)

        wallpaperRepository.getFavWallpapers { wallpapers ->
            adapter.submitList(wallpapers)
        }


        return binding.root
    }

}
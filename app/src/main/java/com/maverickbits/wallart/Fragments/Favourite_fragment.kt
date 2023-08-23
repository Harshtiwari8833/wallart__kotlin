package com.maverickbits.wallart.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager


import com.maverickbits.wallart.Adapter.FavAdapter
import com.maverickbits.wallart.Models.FavModel

import com.maverickbits.wallart.databinding.FragmentFavouriteBinding



class favourite_fragment : Fragment() {

    private lateinit var adapter: FavAdapter
    private val favWallpapersList = mutableListOf<FavModel>()
    private lateinit var binding: FragmentFavouriteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)

//
//        val pref9 =
//            requireContext().getSharedPreferences("userEmail", AppCompatActivity.MODE_PRIVATE)
//        val email = pref9.getString("flag", "")
//        var index: Int = email!!.indexOf('@')
//        val parseEmail = email.substring(0, index)


        adapter = FavAdapter()

        binding.recycleFav.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycleFav.adapter = adapter
        binding.recycleFav.setNestedScrollingEnabled(true)



        return binding.root
    }
}
package com.maverickbits.wallart.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager


import com.maverickbits.wallart.Adapter.FavAdapter
import com.maverickbits.wallart.RoomDatabase.FavDatabase
import com.maverickbits.wallart.RoomDatabase.FavModel
import com.maverickbits.wallart.RoomDatabase.FavRepository
import com.maverickbits.wallart.ViewModel.FavFactoryViewModel
import com.maverickbits.wallart.ViewModel.FavViewModel

import com.maverickbits.wallart.databinding.FragmentFavouriteBinding



class favourite_fragment : Fragment() {

    private lateinit var adapter: FavAdapter
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var favViewModel : FavViewModel



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


        val database = FavDatabase.getDatabase(requireContext()).getFavDao()
        val favRepository = FavRepository(database)

        favViewModel=
            ViewModelProvider(requireActivity(), FavFactoryViewModel(favRepository)).get(FavViewModel::class.java)
        Log.d("FavViewModel",favViewModel.toString())
        favViewModel.list.observe(viewLifecycleOwner, Observer {list->

//            Log.d("fav",list.toString())
            list?.let {

                adapter.submitList(it)

            }
        })



        return binding.root
    }
}
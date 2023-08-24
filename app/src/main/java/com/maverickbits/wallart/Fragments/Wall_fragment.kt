package com.maverickbits.wallart.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.ApiUtilities
import com.maverickbits.wallart.Api.WallModel
import com.maverickbits.wallart.Api.Wallpaper

import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.ViewModel.WallViewModel
import com.maverickbits.wallart.ViewModel.WallViewModelFactory
import com.maverickbits.wallart.databinding.FragmentWallBinding
import com.maverickbits.wallart.pagging.LoaderAdapter
import dagger.hilt.android.AndroidEntryPoint


class wall_fragment : Fragment() {

   private lateinit var wallViewModel: WallViewModel
    private lateinit var binding: FragmentWallBinding
    private lateinit var adapter:WallAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWallBinding.inflate(layoutInflater)

        adapter = WallAdapter(requireContext()){
            val pref =
                requireContext().getSharedPreferences("animation", AppCompatActivity.MODE_PRIVATE)
            val check = pref.getBoolean("flag", false)

            if (check) {
                binding.loading.visibility = View.GONE
                val editor = pref.edit()
                editor.putBoolean("flag", false)
                editor.apply()
        }}
//        {
//
//            val pref =
//                requireContext().getSharedPreferences("animation", AppCompatActivity.MODE_PRIVATE)
//            val check = pref.getBoolean("flag", false)
//            if (check) {
//                val editor = pref.edit()
//                editor.putBoolean("flag", false)
//                editor.apply()
//
//            }

            binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recycler.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
            //implementing loading animation

//        val check = pref.getBoolean("flag", false)
//        if (check){
//
//            val editor = pref.edit()
//            editor.putBoolean("flag", false)
//            editor.apply()
//        }

            val apiInstance = ApiUtilities.getInstance().create(ApiInterface::class.java)
            val repository = WallRepo(apiInstance)
            wallViewModel = ViewModelProvider(
                this,
                WallViewModelFactory(repository)
            ).get(WallViewModel::class.java)

            wallViewModel.list.observe(viewLifecycleOwner, Observer {

                adapter.submitData(lifecycle, it)

            })


        return binding.root
        }


    }

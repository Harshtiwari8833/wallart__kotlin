package com.maverickbits.wallart.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class wall_fragment : Fragment() {

   private lateinit var wallViewModel: WallViewModel
    private lateinit var binding: FragmentWallBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWallBinding.inflate(layoutInflater)
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        val apiInstance = ApiUtilities.getInstance().create(ApiInterface::class.java)
        val repository= WallRepo(apiInstance)
        wallViewModel = ViewModelProvider(this, WallViewModelFactory(repository)).get(WallViewModel::class.java)

        wallViewModel.allWall.observe(viewLifecycleOwner, Observer {

            binding.recycler.adapter=WallAdapter(requireContext(), it.wallpapers as ArrayList<Wallpaper>)

        })


        return binding.root
}




}
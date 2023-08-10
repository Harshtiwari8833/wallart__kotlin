package com.maverickbits.wallart.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Models.WallModel

import com.maverickbits.wallart.R
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



        wallViewModel = ViewModelProvider(this, WallViewModelFactory(WallRepo())).get(WallViewModel::class.java)


        wallViewModel.getwallpaper().observe(viewLifecycleOwner) { it ->
            binding.recycler.adapter = WallAdapter(requireContext(), it as ArrayList<WallModel>)

    }

        return binding.root
}}
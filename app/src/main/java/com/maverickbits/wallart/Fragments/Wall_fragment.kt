package com.maverickbits.wallart.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Models.WallModel

import com.maverickbits.wallart.R
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.ViewModel.WallViewModel
import com.maverickbits.wallart.ViewModel.WallViewModelFactory
import com.maverickbits.wallart.databinding.FragmentWallBinding


class wall_fragment : Fragment() {
//
   private lateinit var wallViewModel: WallViewModel
    private lateinit var binding: FragmentWallBinding
    private lateinit var adapter: WallAdapter

    private var isLoading = false // To track if new data is being loaded
    private var isLastPage = false // To track if all pages are loaded
    private var currentPage = 1 // Track the current page

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentWallBinding.inflate(layoutInflater)
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = WallAdapter(requireContext(), ArrayList())
        binding.recycler.adapter = adapter

        wallViewModel = ViewModelProvider(this, WallViewModelFactory(WallRepo())).get(WallViewModel::class.java)


        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                val threshold = 5 // Number of items from the end to trigger loading more

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - threshold) {
                    // Load more data when user is near the end of the list
                    wallViewModel.loadMoreData(2L)
                }
            }
        })




        wallViewModel.wallpapers.observe(viewLifecycleOwner, Observer { wallpapers ->
            val wallpaperArrayList = ArrayList(wallpapers)
            adapter.updateData(wallpaperArrayList)
        })
        wallViewModel.loadInitialData(4L)
//        wallViewModel.paginatedWallData.observe(viewLifecycleOwner) { dataList ->
//
//            adapter.updateData(dataList)
//        }


//        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = recyclerView.layoutManager as GridLayoutManager
//                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
//                val totalItemCount = layoutManager.itemCount
//
//                if (lastVisibleItemPosition == totalItemCount - 1) {
//                    Log.d("hello", "hellogi")
//                    wallViewModel.loadMoreWallData()
//                }
//            }
//        })


//        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = recyclerView.layoutManager as GridLayoutManager
//                val visibleItemCount = layoutManager.childCount
//                val totalItemCount = layoutManager.itemCount
//                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//
//                if (!isLoading && !isLastPage) {
//                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
//                        && firstVisibleItemPosition >= 0
//                    ) {
//                        // Load the next page of data
//                        wallViewModel.loadMoreWallData()
//                    }
//                }
//            }
//        }
//        )
//        Handler(Looper.getMainLooper()).postDelayed({
//            wallViewModel.loadMoreData(10L)
//        },5000)
//        Handler(Looper.getMainLooper()).postDelayed({
//            wallViewModel.loadMoreData(10L)
//        },10000)

        return binding.root
}}
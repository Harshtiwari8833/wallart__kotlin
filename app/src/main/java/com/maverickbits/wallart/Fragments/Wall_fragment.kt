package com.maverickbits.wallart.Fragments

import android.app.Dialog
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
import androidx.recyclerview.widget.GridLayoutManager
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.ApiUtilities
import com.maverickbits.wallart.R

import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.RoomDatabase.FavDatabase
import com.maverickbits.wallart.RoomDatabase.FavModel
import com.maverickbits.wallart.RoomDatabase.FavRepository
import com.maverickbits.wallart.ViewModel.FavFactoryViewModel
import com.maverickbits.wallart.ViewModel.FavViewModel
import com.maverickbits.wallart.ViewModel.WallViewModel
import com.maverickbits.wallart.ViewModel.WallViewModelFactory
import com.maverickbits.wallart.databinding.FragmentWallBinding
import com.maverickbits.wallart.pagging.LoaderAdapter

private const val TAG = "Wall_fragment"

class wall_fragment : Fragment(),WallAdapter.FavClickListener {

    private lateinit var wallViewModel: WallViewModel
    private lateinit var binding: FragmentWallBinding
    private lateinit var adapter:WallAdapter
    private lateinit var favViewModel : FavViewModel
    private lateinit var dialog: Dialog




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWallBinding.inflate(layoutInflater)

        dialog= Dialog(requireContext(),R.style.TransparentDialog)
        dialog.setContentView(R.layout.progress_dialog)
        dialog.setCancelable(false)
        dialog.show()

        adapter = WallAdapter(requireContext(),this){
            val pref =
                requireContext().getSharedPreferences("animation", AppCompatActivity.MODE_PRIVATE)
            val check = pref.getBoolean("flag", false)

            if (check) {
//               binding.loading.visibility = View.GONE
                dialog.dismiss()
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

           val database = FavDatabase.getDatabase(requireContext()).getFavDao()
           val favRepository = FavRepository(database)

        favViewModel=ViewModelProvider(requireActivity(),FavFactoryViewModel(favRepository))[FavViewModel::class.java]



            wallViewModel = ViewModelProvider(
                requireActivity(),
                WallViewModelFactory(repository)
            ).get(WallViewModel::class.java)

        Log.d(TAG, "onCreateView: vm: ${wallViewModel}")

            wallViewModel.list.observe(viewLifecycleOwner, Observer {

                adapter.submitData(lifecycle, it)

            })



        return binding.root
        }

    override fun onItemClick(imgUrl: String, id: String) {
        val favModel = FavModel(id,imgUrl)
        favViewModel.insert(favModel)

    }

    override fun onItemDelete(imgUrl: String, ImgId: String) {
        val favModel = FavModel(ImgId,imgUrl)
        favViewModel.delete(favModel)
    }

    override fun onResume() {
        super.onResume()
        binding.recycler.adapter = adapter
    }
}

package com.maverickbits.wallart.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.maverickbits.wallart.Adapter.CategoryAdapter
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.R
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.ViewModel.CategoryViewModel
import com.maverickbits.wallart.ViewModel.CategoryViewModelFactory
import com.maverickbits.wallart.ViewModel.WallViewModel
import com.maverickbits.wallart.databinding.ActivityCategoryBinding
import com.maverickbits.wallart.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var category : String
    private  lateinit var catViewModel : CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.wallRecycler.layoutManager = GridLayoutManager(this, 2)
       category = intent.getStringExtra("key").toString()

        catViewModel = ViewModelProvider(this, CategoryViewModelFactory(WallRepo())).get(CategoryViewModel ::class.java)

        catViewModel.getcat(category).observe(this){
            binding.wallRecycler.adapter = CategoryAdapter(this, it as ArrayList<WallModel>)
        }
    }
}
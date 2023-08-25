package com.maverickbits.wallart.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.maverickbits.wallart.Adapter.CategoryAdapter
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.ApiUtilities
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.ViewModel.CategoryViewModel
import com.maverickbits.wallart.ViewModel.CategoryViewModelFactory
import com.maverickbits.wallart.databinding.ActivityCategoryBinding
import com.maverickbits.wallart.pagging.LoaderAdapter

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var category : String
    private lateinit var adapter: CategoryAdapter
    private  lateinit var catViewModel : CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.wallRecycler.layoutManager = GridLayoutManager(this, 2)
       category = intent.getStringExtra("key").toString()

        adapter = CategoryAdapter(this)

        binding.wallRecycler.layoutManager =GridLayoutManager(this, 2)
        binding.wallRecycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        val apiInstance = ApiUtilities.getInstance().create(ApiInterface::class.java)
        val repository = WallRepo(apiInstance)

        catViewModel = ViewModelProvider(this,CategoryViewModelFactory(repository)).get(CategoryViewModel::class.java)

        catViewModel.list.observe(this){
            adapter.submitData(lifecycle, it)
        }



    }
}
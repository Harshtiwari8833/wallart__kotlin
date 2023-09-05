package com.maverickbits.wallart.Activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.maverickbits.wallart.Adapter.CategoryAdapter
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.Api.ApiInterface
import com.maverickbits.wallart.Api.ApiUtilities
import com.maverickbits.wallart.R
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.RoomDatabase.FavDatabase
import com.maverickbits.wallart.RoomDatabase.FavModel
import com.maverickbits.wallart.RoomDatabase.FavRepository
import com.maverickbits.wallart.ViewModel.CategoryViewModel
import com.maverickbits.wallart.ViewModel.CategoryViewModelFactory
import com.maverickbits.wallart.ViewModel.FavFactoryViewModel
import com.maverickbits.wallart.ViewModel.FavViewModel
import com.maverickbits.wallart.databinding.ActivityCategoryBinding
import com.maverickbits.wallart.pagging.LoaderAdapter

class CategoryActivity : AppCompatActivity(), CategoryAdapter.CatFavClickListener {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var category : String
    private lateinit var adapter: CategoryAdapter
    private  lateinit var catViewModel : CategoryViewModel
    private lateinit var dialog: Dialog
    private lateinit var favViewModel : FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.wallRecycler.layoutManager = GridLayoutManager(this, 2)

        dialog= Dialog(this, R.style.TransparentDialog)
        dialog.setContentView(R.layout.progress_dialog)
        dialog.setCancelable(false)
        dialog.show()

        val pref  = getSharedPreferences("Category", MODE_PRIVATE )
        category = pref.getString("value", "").toString()

        //implementing title and Header change based on the category

        if(category.equals("Nature")){
            binding.heading.text = "Nature Walls"
            binding.title.text = "Nature's Symphony: Breathtaking Wallpaper Collection"
        }else if(category.equals("City")){
            binding.heading.text = "City Walls"
            binding.title.text = "Urban Dreams: A City Wallpaper Extravaganza"
        }else if(category.equals("Animal")){
            binding.heading.text = "Animal Walls"
            binding.title.text = "Wild Wonders: Captivating Animal Wallpapers"
        }else if(category.equals("Landscape")){
            binding.heading.text = "Landscape Walls"
            binding.title.text = "Landscape Reverie: Mesmerizing Wallpaper Collection"
        }else if(category.equals("Amoled")){
            binding.heading.text = "Amoled Walls"
            binding.title.text = "Vivid AMOLED Masterpieces: Stunning Wallpaper Selection"
        }else if(category.equals("Dark")){
            binding.heading.text = "Dark Walls"
            binding.title.text = "Eclipse of Shadows: Captivating Dark Wallpaper Collection"
        }else if(category.equals("Anime")){
            binding.heading.text = "Anime Walls"
            binding.title.text = "Anime Allure: Mesmerizing Wallpaper Collection"
        }else if(category.equals("Cars")){
            binding.heading.text = "Cars Walls"
            binding.title.text = "Speed and Style: Exquisite Car Wallpaper Collection"
        }else if(category.equals("Sports")){
            binding.heading.text = "Sports Walls"
            binding.title.text = "Sports Spectacular: Dynamic Wallpaper Collection"
        }else if(category.equals("Space")){
            binding.heading.text = "Space Walls"
            binding.title.text = "Celestial Marvels: Enchanting Space Wallpaper Collection"
        }else if(category.equals("SuperHeros")){
            binding.heading.text = "Superheroes Walls"
            binding.title.text = "Heroic Assemblage: Superheroes Wallpaper Collection"
        }else if(category.equals("ios")){
            binding.heading.text = "iOS Walls"
            binding.title.text = "Illustrious iOS Aesthetics: Wallpaper Collection"
        }else if(category.equals("Solid")){
            binding.heading.text = "Solid Walls"
            binding.title.text = "Stylishly Solid: Contemporary Wallpaper Collection"
        }else if(category.equals("Abstract")){
            binding.heading.text = "Abstract Walls"
            binding.title.text = "Abstract Essence: Mesmerizing Wallpaper Collection"
        }else if(category.equals("Shapes")){
            binding.heading.text = "Shapes Walls"
            binding.title.text = "Shapely Delights: Captivating Wallpaper Collection"
        }else if(category.equals("Minimal")){
            binding.heading.text = "Minimal Walls"
            binding.title.text = "Minimalist Marvels: Sleek Wallpaper Collection"
        }


        // implementing adapter
        adapter = CategoryAdapter(this,this){
            val pref1 = getSharedPreferences("cat_animation", AppCompatActivity.MODE_PRIVATE)
            val check = pref1.getBoolean("flag", false)
            if (check) {
//                binding.loading.visibility = View.GONE
                dialog.dismiss()
                val editor1 = pref1.edit()
                editor1.putBoolean("flag", false)
                editor1.apply()
            }
        }

        binding.wallRecycler.layoutManager =GridLayoutManager(this, 2)
        binding.wallRecycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        val apiInstance = ApiUtilities.getInstance().create(ApiInterface::class.java)
        val repository = WallRepo(apiInstance)

        catViewModel = ViewModelProvider(this,CategoryViewModelFactory(repository,category)).get(CategoryViewModel::class.java)

        catViewModel.list.observe(this){
            adapter.submitData(lifecycle, it)
            Log.d("testing",it.toString())
        }

        val database = FavDatabase.getDatabase(this).getFavDao()
        val favRepository = FavRepository(database)
        favViewModel=ViewModelProvider(this,
            FavFactoryViewModel(favRepository)
        )[FavViewModel::class.java]


    }

    override fun onItemClick(imgUrl: String, ImgId: String) {
        val favModel = FavModel(ImgId,imgUrl)
        favViewModel.insert(favModel)
    }

    override fun onItemDelete(imgUrl: String, ImgId: String) {
        val favModel = FavModel(ImgId,imgUrl)
        favViewModel.delete(favModel)
    }
}
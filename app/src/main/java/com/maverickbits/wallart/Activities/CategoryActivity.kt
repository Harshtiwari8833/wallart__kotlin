package com.maverickbits.wallart.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.ActivityCategoryBinding
import com.maverickbits.wallart.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}
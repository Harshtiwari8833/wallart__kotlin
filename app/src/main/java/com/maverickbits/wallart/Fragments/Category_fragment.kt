package com.maverickbits.wallart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maverickbits.wallart.databinding.FragmentCategoryBinding

class category_fragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater,container,false)

        binding.cardNature.setOnClickListener {


        }

        return binding.root
    }

}
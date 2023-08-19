package com.maverickbits.wallart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.maverickbits.wallart.Activities.CategoryActivity
import com.maverickbits.wallart.Adapter.CategoryAdapter
import com.maverickbits.wallart.databinding.FragmentCategoryBinding

class category_fragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater,container,false)

        binding.cardNature.setOnClickListener {
            sendcat("Nature")
        }
        binding.cardCity.setOnClickListener {
            sendcat("City")
        }
        binding.cardAnimal.setOnClickListener {
            sendcat("Animal")
        }
        binding.cardLandscape.setOnClickListener {
            sendcat("Landscape")
        }
        binding.cardAmoled.setOnClickListener {
            sendcat("Amoled")
        }
        binding.cardDark.setOnClickListener {
            sendcat("Dark")
        }
        binding.cardAnime.setOnClickListener {
            sendcat("Anime")
        }
        binding.cardCars.setOnClickListener {
            sendcat("Cars")
        }
        binding.cardSports.setOnClickListener {
            sendcat("Sports")
        }
        binding.cardSpace.setOnClickListener {
            sendcat("Space")
        }
        binding.cardSuperhero.setOnClickListener {
            sendcat("SuperHeros")
        }
        binding.cardIos.setOnClickListener {
            sendcat("ios")
        }

        binding.cardSolid.setOnClickListener {
            sendcat("Solid")
        }
        binding.cardAbstract.setOnClickListener {
            sendcat("Abstract")
        }
        binding.cardShapes.setOnClickListener {
            sendcat("Shapes")
        }
        binding.cardMinimal.setOnClickListener {
            sendcat("Minimal")
        }

        return binding.root
    }
     fun sendcat(cat: String){
         val intent  = Intent(requireContext(), CategoryActivity :: class.java)
         intent.putExtra("key", cat)
         startActivity(intent)
     }
}
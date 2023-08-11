package com.maverickbits.wallart.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.TokenWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.maverickbits.wallart.Activities.About_Activity
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.FragmentProfileBinding


class profile_fragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        //setting user name in text view
        val pref = requireContext().getSharedPreferences("userName", AppCompatActivity.MODE_PRIVATE)
        val name = pref.getString("flag", "")
        binding.userName.setText("Hi! " +name)

        //setting user profile img
        val pref3 = requireContext().getSharedPreferences("userImg", AppCompatActivity.MODE_PRIVATE)
        val profile = pref3.getString("img", "")

        if (profile.equals("")){
            binding.userImg.setImageDrawable(requireContext().getResources().getDrawable(R.drawable.profile))
        }else{
            Glide.with(requireContext()).load(profile).into(binding.userImg)
        }


        //card about click listener
        binding.cardAbout.setOnClickListener{

            val iAbout = Intent (activity, About_Activity::class.java)
            startActivity(iAbout)
        }
        return binding.root

    }
}
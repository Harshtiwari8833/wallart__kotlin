package com.maverickbits.wallart.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maverickbits.wallart.Activities.About_Activity
import com.maverickbits.wallart.databinding.FragmentProfileBinding


class profile_fragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.cardAbout.setOnClickListener{

            val iAbout = Intent (activity, About_Activity::class.java)
            startActivity(iAbout)
        }
        return binding.root

    }
}
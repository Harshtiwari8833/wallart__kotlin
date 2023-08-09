package com.maverickbits.wallart.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.maverickbits.wallart.R

/**
 * A simple [Fragment] subclass.
 * Use the [favourite_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class favourite_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

}
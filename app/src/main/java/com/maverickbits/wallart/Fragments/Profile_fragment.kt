package com.maverickbits.wallart.Fragments

import android.content.Intent
import android.net.Uri
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
import com.maverickbits.wallart.Activities.Privacy_policy_Activity
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

        binding.cardPolicy.setOnClickListener {

           val iPolicy = Intent (activity, Privacy_policy_Activity::class.java)
            startActivity(iPolicy)

        }

        binding.cardRate.setOnClickListener {
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.maverickbits.statussaverpro&pcampaignid=web_share")
            val iRate = Intent(Intent.ACTION_VIEW,uri)
            startActivity(iRate)
        }

        binding.cardShare.setOnClickListener {

            val iShare = Intent(Intent.ACTION_SEND)
            iShare.type = "text/plain"
            iShare.putExtra(Intent.EXTRA_TEXT,"Share this amazing App: https://play.google.com/store/apps/details?id=com.maverickbits.statussaverpro&pcampaignid=web_share")
            val chooser = Intent.createChooser(iShare,"Share via")
            startActivity(chooser)

        }

        return binding.root

    }
}
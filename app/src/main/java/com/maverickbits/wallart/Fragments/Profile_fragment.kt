package com.maverickbits.wallart.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.maverickbits.wallart.Activities.About_Activity
import com.maverickbits.wallart.Activities.Privacy_policy_Activity
import com.maverickbits.wallart.Activities.SigningWithGoogleActivity
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        loadUserData()

        binding.cardAbout.setOnClickListener {
            startActivity(Intent(activity, About_Activity::class.java))
        }

        binding.cardPolicy.setOnClickListener {
            startActivity(Intent(activity, Privacy_policy_Activity::class.java))
        }

        binding.cardRate.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.cardShare.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.cardLogout.setOnClickListener {
            logout()
        }

        return binding.root
    }

    private fun loadUserData() {
        val sharedPreferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("userName", "User")
        val profileImage = sharedPreferences.getString("userImg", "")

        binding.userName.text = "Hi! $name"

        if (profileImage.isNullOrEmpty()) {
            binding.userImg.setImageResource(R.drawable.profile)
        } else {
            Glide.with(requireContext()).load(profileImage).into(binding.userImg)
        }
    }

    private fun logout() {
        val sharedPreferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
        sharedPreferences.clear()
        sharedPreferences.apply()

        googleSignInClient.signOut().addOnCompleteListener {
            Toast.makeText(requireContext(), "Logged Out!!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), SigningWithGoogleActivity::class.java))
            requireActivity().finish()
        }
    }
}

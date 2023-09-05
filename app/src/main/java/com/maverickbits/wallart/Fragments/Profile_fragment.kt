package com.maverickbits.wallart.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status
import com.maverickbits.wallart.Activities.About_Activity
import com.maverickbits.wallart.Activities.Privacy_policy_Activity
import com.maverickbits.wallart.Activities.SigningWithGoogleActivity
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.FragmentProfileBinding


class profile_fragment : Fragment() {
    private var googleApiClient: GoogleApiClient? = null
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(requireContext())
            .addApi<GoogleSignInOptions>(
                Auth.GOOGLE_SIGN_IN_API,
                gso
            ) // gso is your GoogleSignInOptions instance
            .build()
        googleApiClient!!.connect()
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
//            val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.maverickbits.statussaverpro&pcampaignid=web_share")
//            val iRate = Intent(Intent.ACTION_VIEW,uri)
//            startActivity(iRate)
            Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.cardShare.setOnClickListener {

//            val iShare = Intent(Intent.ACTION_SEND)
//            iShare.type = "text/plain"
//            iShare.putExtra(Intent.EXTRA_TEXT,"Share this amazing App: https://play.google.com/store/apps/details?id=com.maverickbits.statussaverpro&pcampaignid=web_share")
//            val chooser = Intent.createChooser(iShare,"Share via")
//            startActivity(chooser)
            Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_SHORT).show()
        }


        //logout implementing

        binding.cardLogout.setOnClickListener{
            logout()
        }
        return binding.root

    }
    private fun logout() {
        val pref4 = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor3 = pref4.edit()
        editor3.putBoolean("flag1", false)
        editor3.apply()
        try {
            googleApiClient?.let {
                Auth.GoogleSignInApi.signOut(it).setResultCallback { status: Status? ->
                    // Optional: Update your UI or perform any other actions after logout
                    // For example, you can navigate to the login screen
                    Toast.makeText(requireContext(), "Logged Out!!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, SigningWithGoogleActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "error: $e", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onStart() {
        super.onStart()
        googleApiClient!!.connect()
    }

    override fun onStop() {
        super.onStop()
        if (googleApiClient != null && googleApiClient!!.isConnected) {
            googleApiClient!!.disconnect()
        }
    }
}
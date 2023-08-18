package com.maverickbits.wallart.Activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import com.maverickbits.wallart.Fragments.favourite_fragment
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.ActivityOpenFavWallBinding

class OpenFavWallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenFavWallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("wall_pos1")
        val id = intent.getStringExtra("wall_pos")


        Glide.with(this).load(url).into(binding.favImg)

        //bottom sheet

        binding.favImg.setOnClickListener {

            val bottomSheetDialog =
                BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)
            bottomSheetDialog.setContentView(R.layout.favsetwallpaperdilog)

            val remove_fav = bottomSheetDialog.findViewById<ImageView>(R.id.remove_fav)

            remove_fav!!.setOnClickListener {
                val reference3 =
                    FirebaseDatabase.getInstance().reference.child("users").child("harshtiwari8833")
                        .child("favouraite")
                reference3.child(id!!).removeValue().addOnSuccessListener {
                    val fragment =
                        supportFragmentManager.findFragmentByTag("favourite_fragment") as? favourite_fragment
                    Log.d("OpenFavWallActivity", "Fragment found: ${fragment != null}")
                    fragment?.updateListAfterItemRemoval(id)

                    finish()
                }
            }
            bottomSheetDialog.show()
        }

    }
}
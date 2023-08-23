package com.maverickbits.wallart.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.maverickbits.wallart.Activities.OpenWallActivity
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WallAdapter(private val context: Context,private val list :ArrayList<WallModel>)
    :RecyclerView.Adapter<WallAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
        {
            val binding = WallLayoutBinding.bind(itemView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.wall_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = list[position]

        Glide.with(context).load(currentItem.imgUrl)
//            .apply(RequestOptions().override(200, 270))
            .centerCrop()
            .into(holder.binding.wallImg)

        holder.binding.wallcardView.setOnClickListener{
            val intent = Intent(context, OpenWallActivity ::class.java)

            intent.putExtra("wall_pos", position)
            context.startActivity(intent)
        }

     //implementing fav
        val pref9 = context.getSharedPreferences("userEmail", AppCompatActivity.MODE_PRIVATE)
        val email = pref9.getString("flag","")
        val index: Int = email!!.indexOf('@')
      val parseEmail = email.substring(0, index)

      GlobalScope.launch(Dispatchers.IO) {
          val reference = FirebaseDatabase.getInstance().reference.child("users").child(parseEmail)
              .child("favouraite")
          reference.addValueEventListener(object : ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {
                  val list1 = java.util.ArrayList<String?>()
                  for (dataSnapshot in snapshot.children) {
                      val id = dataSnapshot.child("url").getValue(String::class.java)
                      list1.add(id)
                  }
                  kotlinx.coroutines.GlobalScope.launch(Dispatchers.Main) {
                      if (list1.contains(list[position].imgUrl)) {
                          holder.binding.cbHeart.setChecked(true)
                      } else {
                          holder.binding.cbHeart.setChecked(false)
                      }
                  }

              }

              override fun onCancelled(error: DatabaseError) {
//                  Toast.makeText(context, "something went wrong!", Toast.LENGTH_SHORT).show()
              }
          })
      }



        holder.binding.cbHeart.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (holder.binding.cbHeart.isChecked()) {
                val id = list[position].key
                val wall_url = list[position].imgUrl
                GlobalScope.launch(Dispatchers.IO) {
                    val reference =
                        FirebaseDatabase.getInstance().reference.child("users").child(parseEmail)
                            .child("favouraite").child(id!!)
                    val hashMap = HashMap<String, String>()
                    hashMap["id"] = id
                    hashMap["url"] = wall_url!!
                    reference.setValue(hashMap)
                }


            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    val reference3 =
                        FirebaseDatabase.getInstance().reference.child("users").child(parseEmail)
                            .child("favouraite")
                    val id = list[position].key
                    reference3.child(id!!).removeValue().addOnCompleteListener { }.addOnFailureListener {

                    }
                }

            }
        })

    }
//    fun updateData(newData: List<WallModel>) {
//        list.clear()
//        list.addAll(newData)
//        notifyDataSetChanged()
//    }
fun updateData(newList: ArrayList<WallModel>) {
    list.addAll(newList) // Add new items to the existing list
    notifyDataSetChanged()
}
//fun updateData(newData: List<WallModel>) {
////    list.addAll(newData) // Append new data to the existing list
////    notifyDataSetChanged()
//  }
}
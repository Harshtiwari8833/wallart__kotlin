package com.maverickbits.wallart.pagging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maverickbits.wallart.Adapter.CategoryAdapter
import com.maverickbits.wallart.R

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {
    class LoaderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
   val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)

        fun bind(loadState: LoadState){
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        Log.d("checkloder","hiii")
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loader_iteam, parent,false)
        return LoaderViewHolder(view)
    }
}
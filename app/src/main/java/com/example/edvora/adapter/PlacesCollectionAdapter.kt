package com.example.edvora.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.databinding.RecyclerPlacesCollectionBinding
import com.example.edvora.model.PlacesCollectionModel

class PlacesCollectionAdapter (private val placeCollectionArray: Array<PlacesCollectionModel?>) :
    RecyclerView.Adapter<PlacesCollectionAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerPlacesCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.placeNameTextView.text = placeCollectionArray[position]!!.placeName
    }

    override fun getItemCount(): Int {
        return placeCollectionArray.size
    }

    class MyViewHolder(val binding: RecyclerPlacesCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)
}



package com.example.edvora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.databinding.RecyclerRidesCollectionBinding
import com.example.edvora.model.RidesCollectionModel

class RidesCollectionAdapter(private val ridesCollection: ArrayList<RidesCollectionModel>) :
    RecyclerView.Adapter<RidesCollectionAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerRidesCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return ridesCollection.size
    }

    class MyViewHolder(val binding: RecyclerRidesCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)
}


